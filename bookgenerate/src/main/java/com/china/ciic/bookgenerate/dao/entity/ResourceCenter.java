package com.china.ciic.bookgenerate.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 电子书
 * Created by kakasun on 2017/4/10.
 */
//@Table(name = "T_RESOURCECENTER")
public class ResourceCenter implements Serializable {

    Long id;
    Integer version;
    String title;
    String shorttitle;
    Long authorid;
    Timestamp createtime = new Timestamp(System.currentTimeMillis());
    Long createuserid;
    String createusername;
    Integer examineyear;
    String info;
    String keyword;
    Integer readlevel;
    Integer selectstate;
    Integer type;
    Integer length;
    Double money;
    String oriresourceurl;
    String cuturl;
    Integer cutcount;
    String resourceurl;
    Integer sharetype;
    Boolean deleted;
    Integer difficulty;
    String vicetitle;
    Timestamp publishdate = new Timestamp(System.currentTimeMillis());
    String source;
    String provider;
    String producer;
    Timestamp times;
    String precisions;
    String dimension;
    String place;
    Timestamp eventtimes = new Timestamp(System.currentTimeMillis());
    String figureid;
    String uuid;

    private static final long serialVersionUID = 1402466796392585340L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShorttitle() {
        return shorttitle;
    }

    public void setShorttitle(String shorttitle) {
        this.shorttitle = shorttitle;
    }

    public Long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Long authorid) {
        this.authorid = authorid;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    public Long getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Long createuserid) {
        this.createuserid = createuserid;
    }

    public String getCreateusername() {
        return createusername;
    }

    public void setCreateusername(String createusername) {
        this.createusername = createusername;
    }

    public Integer getExamineyear() {
        return examineyear;
    }

    public void setExamineyear(Integer examineyear) {
        this.examineyear = examineyear;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getReadlevel() {
        return readlevel;
    }

    public void setReadlevel(Integer readlevel) {
        this.readlevel = readlevel;
    }

    public Integer getSelectstate() {
        return selectstate;
    }

    public void setSelectstate(Integer selectstate) {
        this.selectstate = selectstate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getOriresourceurl() {
        return oriresourceurl;
    }

    public void setOriresourceurl(String oriresourceurl) {
        this.oriresourceurl = oriresourceurl;
    }

    public String getCuturl() {
        return cuturl;
    }

    public void setCuturl(String cuturl) {
        this.cuturl = cuturl;
    }

    public Integer getCutcount() {
        return cutcount;
    }

    public void setCutcount(Integer cutcount) {
        this.cutcount = cutcount;
    }

    public String getResourceurl() {
        return resourceurl;
    }

    public void setResourceurl(String resourceurl) {
        this.resourceurl = resourceurl;
    }

    public Integer getSharetype() {
        return sharetype;
    }

    public void setSharetype(Integer sharetype) {
        this.sharetype = sharetype;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getVicetitle() {
        return vicetitle;
    }

    public void setVicetitle(String vicetitle) {
        this.vicetitle = vicetitle;
    }

    public Timestamp getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Timestamp publishdate) {
        this.publishdate = publishdate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Timestamp getTimes() {
        return times;
    }

    public void setTimes(Timestamp times) {
        this.times = times;
    }

    public String getPrecisions() {
        return precisions;
    }

    public void setPrecisions(String precisions) {
        this.precisions = precisions;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Timestamp getEventtimes() {
        return eventtimes;
    }

    public void setEventtimes(Timestamp eventtimes) {
        this.eventtimes = eventtimes;
    }

    public String getFigureid() {
        return figureid;
    }

    public void setFigureid(String figureid) {
        this.figureid = figureid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceCenter that = (ResourceCenter) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
