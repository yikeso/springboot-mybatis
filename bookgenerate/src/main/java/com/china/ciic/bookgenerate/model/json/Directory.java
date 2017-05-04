package com.china.ciic.bookgenerate.model.json;

import java.util.List;

/**
 * 目录页的标题目录信息
 * Created by kakasun on 2017/4/20.
 */
public class Directory {
    String id;
    String title;//标题名称
    String url;//章节html页面的绝对路径
    String anchor;//章节锚点
    String pid;//锚点元素的父元素的pid属性
    int chapterParagraphNum;//该章节及其子章节的所有段落数。
    int paragraphNum;//该章节，同一html页的段落数，不包括该章节在别的html页面的子章节
    int level;//标题层级
    boolean newPage = true;
    List<Directory> subDirectory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAnchor() {
        return anchor;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getChapterParagraphNum() {
        return chapterParagraphNum;
    }

    public void setChapterParagraphNum(int chapterParagraphNum) {
        this.chapterParagraphNum = chapterParagraphNum;
    }

    public int getParagraphNum() {
        return paragraphNum;
    }

    public void setParagraphNum(int paragraphNum) {
        this.paragraphNum = paragraphNum;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isNewPage() {
        return newPage;
    }

    public boolean getNewPage() {
        return newPage;
    }
    public void setNewPage(boolean newPage) {
        this.newPage = newPage;
    }

    public List<Directory> getSubDirectory() {
        return subDirectory;
    }

    public void setSubDirectory(List<Directory> subDirectory) {
        this.subDirectory = subDirectory;
    }

}
