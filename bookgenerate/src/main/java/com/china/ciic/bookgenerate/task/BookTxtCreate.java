package com.china.ciic.bookgenerate.task;

import com.china.ciic.bookgenerate.common.constants.ParserConstants;
import com.china.ciic.bookgenerate.common.util.*;
import com.china.ciic.bookgenerate.dao.entity.BookCreateTxtErrorLog;
import com.china.ciic.bookgenerate.dao.entity.ResourceCenter;
import com.china.ciic.bookgenerate.service.impl.BookCreateTxtErrorLogServiceImpl;
import com.china.ciic.bookgenerate.service.impl.ReaderRcServiceImpl;
import com.china.ciic.bookgenerate.service.impl.ResourceCenterServiceImpl;
import com.china.ciic.bookgenerate.handle.encode.CompressContentDecorator;
import com.china.ciic.bookgenerate.handle.encode.EncodeComponent;
import com.china.ciic.bookgenerate.handle.encode.EncodeContentComponent;
import com.china.ciic.bookgenerate.handle.encode.EncodeContentDecorator;
import com.china.ciic.bookgenerate.handle.entity.ChapterEntity;
import com.china.ciic.bookgenerate.handle.entity.UrlEntity;
import com.china.ciic.bookgenerate.model.json.Chapter0;
import com.china.ciic.bookgenerate.model.json.Directory;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;
import org.htmlparser.util.ParserException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 电子书生成txt的定时任务
 * Created by kakasun on 2017/4/10.
 */
@Component
@EnableScheduling
public class BookTxtCreate {
    @Resource
    ReaderRcServiceImpl readerRcRepositories;
    @Resource
    ResourceCenterServiceImpl rcRepositries;
    @Resource(name = "logService")
    BookCreateTxtErrorLogServiceImpl errorLog;
    @Resource
    Environment setSource;
    //日期转字符串
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    private static final Logger log = Logger.getLogger(BookTxtCreate.class);

    /**
     * 定时任务
     */
    @Scheduled(cron="0/1 * * * * ?")
    public void run() {
        String pause = setSource.getProperty("pauseCreateTxtTask","false");
        boolean isPause = "false".equalsIgnoreCase(pause.trim())?false:true;
        if(isPause){
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                log.error(ExceptionUtils.eMessage(e));
            }
            return;
        }
        //查出还没创建txt的电子书
        String devlopEnvironment = setSource.getProperty("devlopEnviorment","true");
        List<ResourceCenter> list;
        if("true".equalsIgnoreCase(devlopEnvironment)) {
            list = rcRepositries.getBookWithOutTxt(2L);
        }else {
            list = rcRepositries.getBookWithOutTxt(3L);
        }
        if (list == null || list.size() == 0) {
            try {
                Thread.sleep(1000 * 60 * 60);
            } catch (InterruptedException e) {
                log.error(ExceptionUtils.eMessage(e));
            }
            return;
        }
        BookCreateTxtErrorLog createError = null;
        String eMessage = null;
        for (ResourceCenter book : list) {
            //给电子书生成txt
            log.info("开始制作：" + book.getTitle());
            try {
                createTxt(book);
                log.info(book.getTitle() + " <=====制作完成\n");
            } catch (Throwable e) {
                eMessage = "id为：" + book.getId() + " 的电子书，制作txt文件失败" + "\n"
                        + ExceptionUtils.eMessage(e);
                log.error(eMessage);
                //将txt转换错误记录到表里
                if (errorLog.countByBookid(book.getId()) == 0) {
                    createError = new BookCreateTxtErrorLog();
                    createError.setBookid(book.getId());
                    createError.setBooktitle(book.getTitle());
                    try {
                        if (eMessage.getBytes("UTF-8").length >= 2000) {
                            eMessage = eMessage.substring(0, 1000);
                        }
                    } catch (UnsupportedEncodingException e1) {
                        log.error(ExceptionUtils.eMessage(e1));
                    }
                    createError.setErrormessage(eMessage);
                    errorLog.save(createError);
                }
            }
            String devlop = setSource.getProperty("devlopEnviorment","true");
            boolean isDev = "true".equalsIgnoreCase(devlop.trim())?true:false;
            //开发环境
            if(isDev) {
                readerRcRepositories.updateMaxresourceid(book.getId(),new Long(2));
            }else {
                readerRcRepositories.updateMaxresourceid(book.getId(),new Long(3));
            }
        }
    }

    /**
     * 根据电子书id制作 某本电子书.
     *
     * @param bookId
     */
    public void makeTxtByBookId(Long bookId) {
        ResourceCenter book = rcRepositries.findById(bookId);
        if (book != null) {
            log.info("优先开始制作：" + book.getTitle());
            try {
                createTxt(book);
                log.info(book.getTitle() + " <=====制作完成\n");
            } catch (Throwable e) {
                String eMessage = "id为：" + book.getId() + " 的电子书，优先制作txt文件失败" + "\n"
                        + ExceptionUtils.eMessage(e);
                log.error(eMessage);
                //将txt转换错误记录到表里
                if (errorLog.countByBookid(book.getId()) == 0) {
                    BookCreateTxtErrorLog createError = new BookCreateTxtErrorLog();
                    createError.setBookid(book.getId());
                    createError.setBooktitle(book.getTitle());
                    try {
                        if (eMessage.getBytes("UTF-8").length >= 2000) {
                            eMessage = eMessage.substring(0, 1000);
                        }
                    } catch (UnsupportedEncodingException e1) {
                        log.error(ExceptionUtils.eMessage(e1));
                    }
                    createError.setErrormessage(eMessage);
                    errorLog.save(createError);
                }
            }
        }
    }

    /**
     * 重试失败的生成txt任务
     * @param bookId
     */
    public void makeTxtAgineByBookId(Long bookId) {
        ResourceCenter book = rcRepositries.findById(bookId);
        if (book != null) {
            log.info("重试失败电子书：" + book.getTitle());
            try {
                createTxt(book);
                log.info(book.getTitle() + " <=====制作完成\n");
                errorLog.markSuccess(book.getId());
            } catch (Throwable e) {
                String eMessage = "id为：" + book.getId() + " 的电子书，重试失败" + "\n"
                        + ExceptionUtils.eMessage(e);
                log.error(eMessage);
                //将txt重试错误记录到表里
                errorLog.faildAngin(book.getId());
            }
        }
    }

    /**
     * 创建电子书txt
     *
     * @param book          电子书
     */
    public void createTxt(ResourceCenter book) throws Exception {
        long id = book.getId();
        int type = book.getType();
        String resourceUrl = book.getResourceurl();
        String domainName = setSource.getProperty("resourceUrl","http://resource.gbxx123.com/");
        //解析电子书目录xml
        UrlEntity url = XMLHandle.getBookUrl(domainName+resourceUrl);
        String eMessage = null;
        if (url == null) {
            eMessage = "id为：" + id + " 的电子书，解析目录xml失败";
            log.error(eMessage);
            throw new RuntimeException(eMessage);
        }
        String devlop = setSource.getProperty("devlopEnviorment","true");
        boolean isDev = "true".equalsIgnoreCase(devlop.trim())?true:false;
        //电子书存放文件
        String bookTxtDir;
        String bookHtmlDir;
        if(isDev){
            bookTxtDir = "e:/bookTxtDir/";
            bookHtmlDir = "e:/bookHtmlDir/";
        }else{
            bookTxtDir =  "./bookTxtDir/";
            bookHtmlDir = "./bookHtmlDir/";
        }
        if (type == 7) {//文章
            bookTxtDir = bookTxtDir + "7article/"
                    + sdf.format(new Date(id * ParserConstants.ID_TO_TIME)) + "/" + id + "/";
            bookHtmlDir = bookHtmlDir + "7article/"
                    + sdf.format(new Date(id * ParserConstants.ID_TO_TIME)) + "/" + id + "/";
        } else {
            bookTxtDir = bookTxtDir + "6book/"
                    + sdf.format(new Date(id * ParserConstants.ID_TO_TIME)) + "/" + id + "/";
            bookHtmlDir = bookHtmlDir + "6book/"
                    + sdf.format(new Date(id * ParserConstants.ID_TO_TIME)) + "/" + id + "/";
        }
        FileUtil.createrFolder(bookTxtDir);
        FileUtil.createrFolder(bookHtmlDir);
        List<Directory> chapterList = getDirListByUrlList(url.getChapterList());
        String cover = url.getCover();//封面图片路径
        Chapter0 chapter0 = new Chapter0();
        chapter0.setBookTitle(book.getTitle());
        //将封面图片url保存为0章节内容
        if (cover != null && !"".equals(cover.trim())) {
            //封面html路径
            String coverHtml = domainName + cover.trim();
            try {
                //封面图片资源路径
                String imageUrl = HtmlHandle.getCoverImageUrl(coverHtml);
                chapter0.setImageUrl(FileUtil.formatFilePath(imageUrl));
            } catch (Exception e) {
                eMessage = "获取id为：" + id + " 电子书封面照片失败" + "\n" + ExceptionUtils.eMessage(e);
                throw new RuntimeException(eMessage);
            }
        }
        String txtPath = null;//txt文件路径
        ChapterEntity chapter = null;
        if (null != url.getDirectory() && !"".equals(url.getDirectory())) {
            chapterList = getChapterListByBcontent(url.getDirectory(), type);
        }
        String createHtml = setSource.getProperty("CREATE_HTML_CONTENT","true");
        boolean isCreate = "true".equalsIgnoreCase(createHtml.trim())?true:false;
        if (null != chapterList && chapterList.size() > 0) {
            EncodeComponent component = null;
            Directory dir1 = null;//当前章url
            Directory dir2 = null;//下一章url
            int listSize = chapterList.size();
            for (int index = 1; index <= listSize; index++) {
                dir1 = chapterList.get(index - 1);
                log.info("...当前制作章节："+dir1.getUrl().substring(dir1.getUrl().lastIndexOf('/')));
                if (dir1.getAnchor() == null) {//没有锚点
                    //获取html，并过滤部分标签
                    component = new EncodeContentComponent(dir1.getUrl(), "utf-8", type,
                            null, null, dir1,setSource);
                } else if (index < listSize) {
                    dir2 = chapterList.get(index);
                    if (dir1.getUrl().equals(dir2.getUrl())) {
                        component = new EncodeContentComponent(dir1.getUrl(), "utf-8",
                                type, dir1.getAnchor(), dir2.getAnchor(), dir1,setSource);
                    } else {
                        component = new EncodeContentComponent(dir1.getUrl(), "utf-8",
                                type, dir1.getAnchor(), null, dir1,setSource);
                    }
                } else {//最后一章
                    component = new EncodeContentComponent(dir1.getUrl(), "utf-8", type,
                            dir1.getAnchor(), null, dir1,setSource);
                }
                //压缩
                component = new CompressContentDecorator(component);
                //编码
                component = new EncodeContentDecorator(component);
                chapter = component.operation();
                if(chapter.getParagraph() == 0){
                    throw new RuntimeException("序号为:"+index+"的章节 段落数为0");
                }
                txtPath = bookTxtDir + dir1.getId() + ".txt";
                FileUtil.saveFile(chapter.getContent(), txtPath);
                if (isCreate) {
                    txtPath = bookHtmlDir + dir1.getId() + ".txt";
                    FileUtil.saveFile(chapter.getHtmlContent(), txtPath);
                }
            }
            chapter0.getDirectories().clear();
            //统计章节所有段落，包括其子章节,将子章节设置为空，减小保存体积
            for (int index = 1; index <= listSize; index++) {
                dir1 = chapterList.get(index - 1);
                dir1.setChapterParagraphNum(countChapterParagraphNum(dir1));
                if(dir1.getLevel() == 1){
                    chapter0.getDirectories().add(dir1);
                }
            }
        } else if (null != url.getChapters() && !"".equals(url.getChapters().trim())) {//没有章节只有一块内容
            Directory dir = new Directory();
            dir.setUrl(FileUtil.formatFilePath(url.getChapters()));
            dir.setLevel(1);
            dir.setId(Integer.toString(1));
            //获取html，并过滤部分标签
            EncodeComponent component = new EncodeContentComponent(url.getChapters().trim(), "utf-8", type,
                    null, null, dir,setSource);
            //压缩
            component = new CompressContentDecorator(component);
            //编码
            component = new EncodeContentDecorator(component);
            chapter = component.operation();
            if(chapter.getParagraph() == 0){
                throw new RuntimeException("序号为:1的章节 段落数为0");
            }
            dir.setChapterParagraphNum(dir.getParagraphNum());
            chapter0.getDirectories().add(dir);
            txtPath = bookTxtDir + dir.getId() + ".txt";
            FileUtil.saveFile(chapter.getContent(), txtPath);
            if (isCreate) {
                txtPath = bookHtmlDir + dir.getId() + ".txt";
                FileUtil.saveFile(chapter.getHtmlContent(), txtPath);
            }
        }
        try {
            txtPath = bookTxtDir + 0 + ".txt";
            FileUtil.saveFile(JsonUtility.getJsonfromBean(chapter0), txtPath);
            if (isCreate) {
                txtPath = bookHtmlDir + 0 + ".txt";
                FileUtil.saveFile(JsonUtility.getJsonfromBean(chapter0), txtPath);
            }
        } catch (JsonProcessingException e) {
            eMessage = "id为：" + id + " 电子书封面照片url及各章节段落书，持久化失败" + "\n" + ExceptionUtils.eMessage(e);
            throw new RuntimeException(eMessage);
        }
    }

    /**
     * 解析b_content文件获得章节url
     *
     * @param path
     * @param type
     * @return
     */
    private List<Directory> getChapterListByBcontent(String path, int type) throws IOException, ParserException {
        String dirPath = new File(path).getParent() + "/";
        //通过资源路径，判断文件的字符集
        String fileCode = "UTF-8";
        URL url = new URL("http://resource.gbxx123.com/" + path);
        InputStream inputStream = null;
        String indexContent = null;
        byte[] htmlByte = null;
        if (type == 7) {//文章
            try {
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(10 * 1000);
                inputStream = con.getInputStream();
                htmlByte = FileUtil.getBytes(inputStream);
                indexContent = new String(htmlByte, fileCode);
            } catch (Exception e) {
                //文章b_content.xhtnl文件解析失败，则跳过此资源，不将错误记录入数据库内。
                log.error(ExceptionUtils.eMessage(e));
                return new ArrayList<Directory>();
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        } else if (type == 6) {//电子书
            try {
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(10 * 1000);
                inputStream = con.getInputStream();
                htmlByte = FileUtil.getBytes(inputStream);
                indexContent = new String(htmlByte, fileCode);
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }
        if(indexContent == null || indexContent.trim().length()==0){
            throw new RuntimeException("电子书b_content目录页为空");
        }
//      log.info(indexContent);
        Document doc = Jsoup.parse(indexContent);
        Elements a = doc.getElementsByTag("a");
        return getChapterUrlList(dirPath, a);
    }

    /**
     * 从目录的a标签中提起各章节的url
     * @param dirPath
     * @param all
     */
    private List<Directory> getChapterUrlList(String dirPath, Elements all) {
        if (all == null || all.size() == 0) {
            throw new RuntimeException("电子书目录页无章节列表");
        }
        //获取一级标题
        List<Directory> dirAll = getSubDir(null,all,dirPath);
        List<Directory> list = new ArrayList<Directory>(50);
        //给标题添加子标题，包括子标题的子标题,并得到所有标题的按顺序的列表
        for(Directory d:dirAll){
            list.add(d);
            setSubDirectory(d,all,dirPath,list);
        }
        dirAll.clear();//清空列表
        //对比上一页，是否是新起的一个html页面
        //只保留新起html的章节,和一级标题
        String lastUrl = "";
        for (Directory d:list){
            if(lastUrl.equals(d.getUrl())){
                d.setNewPage(false);
            }else {
                lastUrl = d.getUrl();
            }
            if(d.isNewPage() || d.getLevel() == 1){
                dirAll.add(d);
            }
        }
        return dirAll;
    }

    /**
     * 给Directory对象添加subDirectory属性
     */
    private void setSubDirectory(Directory parent,Elements a,String dirPath,List<Directory> all){
        if(parent == null){
            return;
        }
        List<Directory> list = getSubDir(parent,a,dirPath);
        if(list == null || list.size() == 0){
            return;
        }else{
            parent.setSubDirectory(list);
            for(Directory d:list){
                all.add(d);
                setSubDirectory(d,a,dirPath,all);
            }
        }
    }

    /**
     * 根据父目录获取子目录
     * @param parent
     * @param a
     * @param dirPath
     * @return
     */
    private List<Directory> getSubDir(Directory parent,Elements a,String dirPath){
        List<Directory> list = new ArrayList<Directory>(50);
        Element e;
        Directory dir;
        String text;
        String[] ss;
        String anchor;
        String url;
        String href;
        String partAnchor;
        if(parent == null){//获取一级标题
            int n = 1;
            for(int i = 1;i <= a.size();i++){
                e = a.get(i-1);
                text = e.text();
                if(text == null || text.trim().length() == 0){//跳过空标题
                    continue;
                }
                dir = new Directory();
                dir.setTitle(text);
                dir.setLevel(1);
                href = e.attr("href");
                if(href == null || href.trim().length() == 0){
                    continue;
                }
                if(href.indexOf('#') != -1){
                    ss = href.split("#");
                    anchor = ss[1];
                    if(anchor.indexOf('-') != -1){
                        continue;
                    }else {
                        url = dirPath + ss[0];
                        dir.setUrl(FileUtil.formatFilePath(url));
                        dir.setAnchor(anchor);
                    }
                }else {
                    url = dirPath + href;
                    dir.setUrl(FileUtil.formatFilePath(url));
                }
                dir.setId(Integer.toString(n++));
                list.add(dir);
            }
        }else if(parent.getAnchor() == null){//父标题无锚点，则该标题无子标题
            return null;
        }else {
            int n = 1;
            for(int i = 0;i < a.size();i++){
                e = a.get(i);
                text = e.text();
                //跳过空标题
                if(text == null || text.trim().length() == 0){
                    continue;
                }
                dir = new Directory();
                dir.setTitle(text);
                dir.setLevel(parent.getLevel() + 1);
                href = e.attr("href");
                if(href == null || href.trim().length() == 0){
                    continue;
                }
                if(href.indexOf('#') != -1){
                    ss = href.split("#");
                    anchor = ss[1];
                    if(anchor.length() <= parent.getAnchor().length()){
                        continue;
                    }
                    int end = anchor.indexOf('-',parent.getAnchor().length());
                    if(end == -1){
                        continue;
                    }
                    //截取从0到付标题锚点长度后的第一个“-”
                    partAnchor = anchor.substring(0,end);
                    //不是以父标题锚点，作为开头的锚点则不是该标题的子标题
                    if(!partAnchor.equals(parent.getAnchor())){
                        continue;
                    }else if(anchor.indexOf('-') == -1){//没有“-”的都不是一级标题。不会是子标题
                        continue;
                    }else if(StringUtils.countFromIndex(anchor,'-',parent.getAnchor().length()) == 1){
                        //从父标题锚点结束位置开始统计“-”个数，如果为1，则刚好是父标题的下一级标题
                        dir.setId(parent.getId() + '-' + n++);
                        url = dirPath + ss[0];
                        dir.setUrl(FileUtil.formatFilePath(url));
                        dir.setAnchor(anchor);
                    }else {
                        continue;
                    }
                }else {
                    continue;
                }
                list.add(dir);
            }
        }
        return list;
    }

    private List<Directory> getDirListByUrlList(List<String> list){
        List<Directory> dirList = new ArrayList<Directory>(50);
        if(list == null || list.size() == 0){
            return dirList;
        }
        Directory dir;
        int id = 0;
        String[] ss;
        for(String s : list){
            dir = new Directory();
            dir.setId(Integer.toString(id++));
            dir.setLevel(1);
            if(s.indexOf('#') != -1){
                ss = s.split("#");
                dir.setUrl(FileUtil.formatFilePath(ss[0]));
                dir.setAnchor(ss[1]);
            }else {
                dir.setUrl(FileUtil.formatFilePath(s));
            }
            dirList.add(dir);
        }
        return dirList;
    }

    /**
     * 统计章节及其子章节的段落书
     * @param dir
     * @return
     */
    private int countChapterParagraphNum(Directory dir){
        int paragraphNum = 0;
        if(dir == null){
            return paragraphNum;
        }
        paragraphNum += dir.getParagraphNum();
        List<Directory> list = dir.getSubDirectory();
        if(list == null || list.size() == 0){
            return paragraphNum;
        }
        for(Directory d:list){
            if(d.isNewPage()) {//子章节另起新的html页的，加入统计
                paragraphNum += countChapterParagraphNum(d);
            }
        }
        return paragraphNum;
    }

    /**
     * 创建电子书txt文件的任务封装打包
     */
    class CreateTxtRun implements Runnable{

        ResourceCenter book;
        BookCreateTxtErrorLogServiceImpl errorLog;

        public CreateTxtRun(ResourceCenter book,BookCreateTxtErrorLogServiceImpl errorLog){
            this.book = book;
            this.errorLog = errorLog;
        }

        @Override
        public void run() {
            //给电子书生成txt
            log.info("开始制作：" + book.getTitle());
            try {
                createTxt(book);
                log.info(book.getTitle() + " <=====制作完成\n");
            } catch (Exception e) {
                String eMessage = "id为：" + book.getId() + " 的电子书，制作txt文件失败" + "\n"
                        + ExceptionUtils.eMessage(e);
                log.error(eMessage);
                //将txt转换错误记录到表里
                if (errorLog.countByBookid(book.getId()) == 0) {
                    BookCreateTxtErrorLog createError = new BookCreateTxtErrorLog();
                    createError.setBookid(book.getId());
                    createError.setBooktitle(book.getTitle());
                    try {
                        if (eMessage.getBytes("UTF-8").length >= 2000) {
                            eMessage = eMessage.substring(0, 1000);
                        }
                    } catch (UnsupportedEncodingException e1) {
                        log.error(ExceptionUtils.eMessage(e1));
                    }
                    createError.setErrormessage(eMessage);
                    errorLog.save(createError);
                }
            }
        }
    }
}
