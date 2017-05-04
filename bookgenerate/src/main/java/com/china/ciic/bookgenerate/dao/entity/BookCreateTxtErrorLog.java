package com.china.ciic.bookgenerate.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 创建电子书的txt文件失败日志
 * Created by kakasun on 2017/4/12.
 */
//@Table(name = "book_create_txt_error_log")
public class BookCreateTxtErrorLog implements Serializable {

    Long id;
    Long bookid;//电子书id
    String booktitle;//电子书名称
    Timestamp createtime;//创建日期
    Timestamp lastmodify = new Timestamp(System.currentTimeMillis());//上次修改日期
    String errormessage;//错误原因
    Integer status = 0;//状态，-1表示处理成功，0表示未处理，1表示重试仍失败；2表示已忽略;3表示正在重试中
    Integer chapternum;//章节序号，0表示封面图片。

    private static final long serialVersionUID = -6418323766865017786L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookid() {
        return bookid;
    }

    public void setBookid(Long bookid) {
        this.bookid = bookid;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Timestamp getLastmodify() {
        return lastmodify;
    }

    public void setLastmodify(Timestamp lastmodify) {
        this.lastmodify = lastmodify;
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getChapternum() {
        return chapternum;
    }

    public void setChapternum(Integer chapterNum) {
        this.chapternum = chapterNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookCreateTxtErrorLog that = (BookCreateTxtErrorLog) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "BookCreateTxtErrorLog{" +
                "id=" + id +
                ", bookid=" + bookid +
                ", booktitle='" + booktitle + '\'' +
                ", createtime=" + createtime +
                ", lastmodify=" + lastmodify +
                ", errormessage='" + errormessage + '\'' +
                ", status=" + status +
                ", chapterNum=" + chapternum +
                '}';
    }
}
