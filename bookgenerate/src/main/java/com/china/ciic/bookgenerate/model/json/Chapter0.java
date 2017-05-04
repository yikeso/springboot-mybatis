package com.china.ciic.bookgenerate.model.json;

import java.util.ArrayList;
import java.util.List;

/**
 * 章节0
 * Created by kakasun on 2017/4/13.
 */
public class Chapter0 extends BaseResponse{
    String imageUrl;//封面图片
    String bookTitle;//书名
    List<Directory> directories = new ArrayList<Directory>(50);//目录

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public List<Directory> getDirectories() {
        return directories;
    }

    public void setDirectories(List<Directory> directories) {
        this.directories = directories;
    }
}
