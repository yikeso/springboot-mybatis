package com.china.ciic.bookgenerate.controller;

import com.china.ciic.bookgenerate.model.json.BaseResponse;
import com.china.ciic.bookgenerate.service.BookContentService;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 获取电子书的章节内容，封面图片
 * Created by kakasun on 2017/4/11.
 */
@RestController
@RequestMapping("/bookcontent")
public class BookContentControl {
    @Resource(name = "bookContentService")
    private BookContentService bookContentService;
    @Resource
    Environment setSource;

    /**
     * 响应获取电子书封面图片的请求
     * @param bookId
     * @return
     */
    @RequestMapping("/coverimage")
    public BaseResponse coverImage(Long bookId){
        System.out.println("bookId:"+bookId);
        BaseResponse result = null;
        if(bookId == null){
            result = new BaseResponse();
            result.setStatus(1);
            result.setMessage("电子书id不得为空");
            return result;
        }
        result = bookContentService.getCoverImageUrl(bookId);
        return result;
    }

    /**
     * 根据电子书id，和章节序号，获取电子书的章节内容。
     * @param bookId
     * @param chapterId
     * @param compress true返回压缩内容，false返回解压缩内容
     * @return
     */
    @RequestMapping("/chaptercontent")
    public BaseResponse chapterContent(Long bookId, String chapterId,String compress){
        BaseResponse result = null;
        if(bookId == null){
            result = new BaseResponse();
            result.setStatus(1);
            result.setMessage("电子书id不得为空");
            return result;
        }
        boolean isCompress = compress == null || "false".equalsIgnoreCase(compress)?false:true;
        if(chapterId == null){
            chapterId = "1";
        }
        result = bookContentService.getContentByIdAndChapterNum(bookId,chapterId,isCompress);
        return result;
    }

    /**
     * 根据电子书id，和章节序号，获取电子书的章节内容。
     * @param bookId
     * @param chapterId
     * @param compress true返回压缩内容，false返回解压缩内容
     * @return
     */
    @RequestMapping("/chapterhtmlcontent")
    public BaseResponse chapterHtmlContent(Long bookId, String chapterId,String compress){
        BaseResponse result = null;
        String createHtml = setSource.getProperty("CREATE_HTML_CONTENT","true");
        boolean isCreate = "true".equalsIgnoreCase(createHtml.trim())?true:false;
        //判断项目模式，是否制作 增加部分html标签 的章节内容
        if(!isCreate){
            result = new BaseResponse();
            result.setStatus(7);
            result.setMessage("项目模式，不制作此类型章节内容。");
            return result;
        }
        if(bookId == null){
            result = new BaseResponse();
            result.setStatus(1);
            result.setMessage("电子书id不得为空");
            return result;
        }
        boolean isCompress = compress == null || "false".equalsIgnoreCase(compress)?false:true;
        if(chapterId == null){
            chapterId = "1";
        }
        result = bookContentService.getHtmlContentByIdAndChapterNum(bookId,chapterId,isCompress);
        return result;
    }

    /**
     * 返回文章目录信息
     * @param bookId
     * @return
     */
    @RequestMapping("/directoryinfo")
    public BaseResponse directoryInfo(Long bookId){
        BaseResponse result = null;
        if(bookId == null){
            result = new BaseResponse();
            result.setStatus(1);
            result.setMessage("电子书id不得为空");
            return result;
        }
        result = bookContentService.getDirectoryInfo(bookId);
        return result;
    }
}
