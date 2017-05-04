package com.china.ciic.bookgenerate.service.impl;

import com.china.ciic.bookgenerate.common.constants.ParserConstants;
import com.china.ciic.bookgenerate.common.util.ExceptionUtils;
import com.china.ciic.bookgenerate.common.util.FileUtil;
import com.china.ciic.bookgenerate.common.util.JsonUtility;
import com.china.ciic.bookgenerate.handle.decode.DeCompressContentDecorator;
import com.china.ciic.bookgenerate.handle.decode.DecodeComponent;
import com.china.ciic.bookgenerate.handle.decode.DecodeContentComponent;
import com.china.ciic.bookgenerate.handle.decode.DecodeContentDecorator;
import com.china.ciic.bookgenerate.model.json.BaseResponse;
import com.china.ciic.bookgenerate.model.json.Chapter0;
import com.china.ciic.bookgenerate.model.json.OneResult;
import com.china.ciic.bookgenerate.service.BookContentService;
import com.china.ciic.bookgenerate.task.BookTxtCreate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 获取电子书封面图片和章节内容的service
 * Created by kakasun on 2017/4/11.
 */
@Service("bookContentService")
public class BookContentServiceImpl implements BookContentService {
    //线程池。
    public final static ExecutorService pool;
    @Resource
    BookTxtCreate bookTxtCreate;
    @Resource
    BookCreateTxtErrorLogServiceImpl errorLog;
    ResourceCenterServiceImpl resourceCenterRepositories;
    @Resource
    Environment setSource;
    String bookTxtDir;
    String bookHtmlDir;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private static Logger log = LoggerFactory.getLogger(BookContentServiceImpl.class);
    static {
        pool = Executors.newFixedThreadPool(2);
    }

    /**
     * 根据电子id获取封面图片
     * @param bookId
     * @return
     */
    @Override
    public BaseResponse getCoverImageUrl(long bookId) {
        BaseResponse result = new BaseResponse();
        String path = getBookTxtDir(bookId) + "0.txt";
        String content = null;
        int n = 0;
        try {
            content = FileUtil.readerText(path, "UTF-8");
        } catch (FileNotFoundException e) {
            log.error(ExceptionUtils.eMessage(e));
            File dir = new File(path).getParentFile();
            if(!dir.exists()){
                result.setStatus(2);
                result.setMessage("电子书正在制作，请稍后再试。");
                //创建目录
                dir.mkdirs();
                //新建任务进行制作
                pool.execute(new MakeTxt(bookId,bookTxtCreate));
            }else {
                result.setStatus(4);
                result.setMessage("电子书制作失败，请联系管理员。或正在制作，请稍后再试。");
            }
        } catch (IOException e) {
            log.error(ExceptionUtils.eMessage(e));
            result.setStatus(3);
            result.setMessage("获取电子书封面图片失败，请稍后再试。");
        }
        if(content == null){
            return result;
        }
        Chapter0 chapter0 = JsonUtility.getBeanformJson(content,Chapter0.class);
        if(chapter0 == null){
            result.setStatus(6);
            result.setMessage("系统故障，请稍后再试");
            log.error("解析chapter0 json字符串失败");
            return result;
        }else if(chapter0.getImageUrl() == null){
            result.setStatus(5);
            result.setMessage("此电子书无封面图片");
            return result;
        }
        OneResult<String> oneResult = new OneResult<String>();
        oneResult.setOne(FileUtil.formatFilePath(chapter0.getImageUrl()));
        return oneResult;
    }

    /**
     * 根据电子书id，章节序号，获取章节内容
     * @param bookId
     * @param chapterNum 从1开始
     * @param compress true 返回解压缩前的内容，false，返回解压缩后的内容
     * @return
     */
    @Override
    public BaseResponse getContentByIdAndChapterNum(long bookId, String chapterNum,boolean compress) {
        String path = getBookTxtDir(bookId) + chapterNum +".txt";
        BaseResponse result;
        if("0".equalsIgnoreCase(chapterNum)){
            result = deCompressContentByPathBookId(path,bookId,false);
            if(result instanceof OneResult){
                String content = (String)((OneResult) result).getOne();
                result = JsonUtility.getBeanformJson(content,Chapter0.class);
            }
        }else{
            result = deCompressContentByPathBookId(path,bookId,!compress);
        }
        return result;
    }

    /**
     * 获取增加了html标签的章节内容
     * @param bookId
     * @param chapterNum
     * @param compress true 返回解压缩前的内容，false，返回解压缩后的内容
     * @return
     */
    @Override
    public BaseResponse getHtmlContentByIdAndChapterNum(long bookId, String chapterNum,boolean compress) {
        String path = getBookHtmlDir(bookId) + chapterNum +".txt";
        BaseResponse result;
        if("0".equalsIgnoreCase(chapterNum)){
            result = deCompressContentByPathBookId(path,bookId,false);
            if(result instanceof OneResult){
                String content = (String)((OneResult) result).getOne();
                result = JsonUtility.getBeanformJson(content,Chapter0.class);
            }
        }else{
            result = deCompressContentByPathBookId(path,bookId,!compress);
        }
        return result;
    }

    /**
     * 返回目录信息
     * @param bookId
     * @return
     */
    @Override
    public BaseResponse getDirectoryInfo(Long bookId) {
        return getContentByIdAndChapterNum(bookId,"0",true);
    }

    /**
     * 获取非0章节内容，解压缩
     * @param path
     * @param bookId
     * @param deCompress 是否要解压缩
     * @return
     */
    private BaseResponse deCompressContentByPathBookId(String path,long bookId,boolean deCompress){
        BaseResponse result = new BaseResponse();
        String content = null;
        int n = 0;
        try {
            content = FileUtil.readerText(path, "UTF-8");
        } catch (FileNotFoundException e) {
            log.error(ExceptionUtils.eMessage(e));
            File dir = new File(path).getParentFile();
            if(!dir.exists()){
                result.setStatus(2);
                result.setMessage("电子书正在制作，请稍后再试。");
                //创建目录
                dir.mkdirs();
                //新建任务进行制作
                pool.execute(new MakeTxt(bookId,bookTxtCreate));
            }else {
                n = errorLog.countByBookid(bookId);
                if(n>0){
                    result.setStatus(4);
                    result.setMessage("电子书制作失败，请联系管理员。");
                }else{
                    result.setStatus(5);
                    result.setMessage("电子书无该章节。或正在制作，请稍后再试。");
                }
            }
        } catch (IOException e) {
            log.error(ExceptionUtils.eMessage(e));
            result.setStatus(3);
            result.setMessage("获取电子书章节内容失败，请稍后再试。");
        }
        if(content == null){
            return result;
        }
        OneResult<String> oneResult = new OneResult<String>();
        //如果需要解压缩
        DecodeComponent component = new DecodeContentComponent(content);
        if(deCompress) {
            //内容解码
            component = new DecodeContentDecorator(component);
            //内容解压缩
            component = new DeCompressContentDecorator(component);
        }
        content = component.operation();
        oneResult.setOne(content);
        return oneResult;
    }

    /**
     * 获取电子书过滤标签后的文本存放文件夹路径
     * @param bookId
     * @return
     */
    private String getBookTxtDir(Long bookId){
        String dir;
        int type = resourceCenterRepositories.getBookTypeById(bookId);
        String devlop = setSource.getProperty("devlopEnviorment","true");
        boolean isDev = "true".equalsIgnoreCase(devlop.trim())?true:false;
        if(isDev){
            bookTxtDir = "e:/bookTxtDir/";
            bookHtmlDir = "e:/bookHtmlDir/";
        }else{
            bookTxtDir =  "./bookTxtDir/";
            bookHtmlDir =  "./bookHtmlDir/";
        }
        if (type == 7) {
           dir = bookTxtDir + "7article/"
                    + sdf.format(new Date(bookId * ParserConstants.ID_TO_TIME))
                    + "/" + bookId + "/";
        }else {
            dir = bookTxtDir + "6book/"
                    + sdf.format(new Date(bookId * ParserConstants.ID_TO_TIME))
                    + "/" + bookId + "/";
        }
        return dir;
    }

    /**
     * 获取电子书增加标签后的文本存放文件夹路径
     * @param bookId
     * @return
     */
    private String getBookHtmlDir(Long bookId){
        String dir;
        int type = resourceCenterRepositories.getBookTypeById(bookId);
        String devlop = setSource.getProperty("devlopEnviorment","true");
        boolean isDev = "true".equalsIgnoreCase(devlop.trim())?true:false;
        if(isDev){
            bookTxtDir = "e:/bookTxtDir/";
            bookHtmlDir = "e:/bookHtmlDir/";
        }else{
            bookTxtDir = "./bookTxtDir/";
            bookHtmlDir = "./bookHtmlDir/";
        }
        if (type == 7) {
            dir = bookHtmlDir + "7article/"
                    + sdf.format(new Date(bookId * ParserConstants.ID_TO_TIME))
                    + "/" + bookId + "/";
        }else {
            dir = bookHtmlDir + "6book/"
                    + sdf.format(new Date(bookId * ParserConstants.ID_TO_TIME))
                    + "/" + bookId + "/";
        }
        return dir;
    }

    /**
     * 封装制作特定id的电子书txt文件
     */
    class MakeTxt implements Runnable{
        Long bookId;
        BookTxtCreate bookTxtCreate;
        public MakeTxt(Long bookId,BookTxtCreate bookTxtCreate){
            this.bookId = bookId;
            this.bookTxtCreate = bookTxtCreate;
        }

        @Override
        public void run() {
            bookTxtCreate.makeTxtByBookId(bookId);
        }
    }
}
