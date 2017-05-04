package com.china.ciic.bookgenerate.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 记录html转txt的进度。
 * Created by kakasun on 2017/4/10.
 */
//@Table(name = "T_READER_RC")
public class ReaderRc implements Serializable {

    Long id;
    Long maxresourceid;

    private static final long serialVersionUID = -7134699005136502679L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaxresourceid() {
        return maxresourceid;
    }

    public void setMaxresourceid(Long maxresourceid) {
        this.maxresourceid = maxresourceid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReaderRc readerRc = (ReaderRc) o;

        return id.equals(readerRc.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
