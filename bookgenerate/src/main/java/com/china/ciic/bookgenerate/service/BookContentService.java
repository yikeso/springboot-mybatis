package com.china.ciic.bookgenerate.service;


import com.china.ciic.bookgenerate.model.json.BaseResponse;

/**
 * 获取电子书的内容
 * Created by kakasun on 2017/4/11.
 */
public interface BookContentService {
    /**
     * 根据电子书id获取电子书封面图片的url
     * @param bookId
     * @return
     */
    BaseResponse getCoverImageUrl(long bookId);

    /**
     * 根据电子书id和章节序号获取过滤部分标签的章节内容
     * @param bookId
     * @param chapterNum
     * @return
     */
    BaseResponse getContentByIdAndChapterNum(long bookId, String chapterNum, boolean compress);

    /**
     * 根据电子书id和章节序号获取增加html标签的章节内容
     * @param bookId
     * @param chapterNum
     * @return
     */
    BaseResponse getHtmlContentByIdAndChapterNum(long bookId, String chapterNum, boolean compress);

    BaseResponse getDirectoryInfo(Long bookId);
}
