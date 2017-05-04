package com.china.ciic.bookgenerate.dao.mapper;

import com.china.ciic.bookgenerate.dao.entity.ResourceCenter;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by kakasun on 2017/5/3.
 */
public interface ResourceCenterMapper {
    /**
     * 超出还没有压缩txt的电子书记录20条
     * @return
     */
    @Select("Select * from T_RESOURCECENTER WHERE deleted='0' AND type in (6,7) "
            + "AND id>(SELECT MAXRESOURCEID FROM T_READER_RC rc WHERE rc.ID=#{readerId})")
    List<ResourceCenter> getBookWithOutTxt(Long readerId);

    @Select("SELECT ID,VERSION,TITLE,SHORTTITLE,AUTHORID,CREATETIME,CREATEUSERID,"
        + "CREATEUSERNAME,EXAMINEYEAR,INFO,KEYWORD,READLEVEL,SELECTSTATE,TYPE,LENGTH,"
        + "MONEY,ORIRESOURCEURL,CUTURL,CUTCOUNT,RESOURCEURL,SHARETYPE,DELETED,"
        + "DIFFICULTY,VICETITLE,PUBLISHDATE,SOURCE,PROVIDER,PRODUCER,TIMES,PRECISIONS,"
        + "DIMENSION,PLACE,EVENTTIMES,FIGUREID,UUID FROM T_RESOURCECENTER WHERE id = ?")
    ResourceCenter findById(Long bookId);

    /**
     * 获取电子书资源的类型
     * 6:epub电子书转换而来；7:article文章转换而来
     * @param id
     * @return
     */
    @Select("SELECT t.type FROM T_RESOURCECENTER t WHERE t.id = #{id}")
    int getBookTypeById(Long id);
}
