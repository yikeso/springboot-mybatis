package com.china.ciic.bookgenerate.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Created by once on 2017/5/3.
 */
public interface ReaderRcMapper {
    @Update("Update T_READER_RC Set maxresourceid = #{maxresourceid} Where id = #{id}")
    void updateMaxresourceid(@Param("maxresourceid")Long maxresourceid,@Param("id") Long id);
}
