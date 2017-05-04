package com.china.ciic.bookgenerate.dao.mapper;

import com.china.ciic.bookgenerate.dao.entity.BookCreateTxtErrorLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by kakasun on 2017/5/3.
 */
public interface BookCreateTxtErrorLogMapper {
    /**
     * 重试失败某本电子书
     * @param bookid 电子书id
     */
    @Update("UPDATE book_create_txt_error_log SET status = 3,lastmodify = CURRENT_TIMESTAMP WHERE bookid = #{bookid}")
    void faildAngin(Long bookid);

    /**
     * 标记某本电子书正在重试中
     * @param bookid 电子书id
     */
    @Update("UPDATE book_create_txt_error_log SET status = 3,lastmodify = CURRENT_TIMESTAMP WHERE bookid = #{bookid}")
    void tryAngining(Long bookid);

    /**
     * 根据状态查找错误
     * @param status
     * @return
     */
    @Select("<script> Select * From book_create_txt_error_log Where status in " +
            "<foreach collection='array' item = 's' index='index' " +
            " open='(' close=')' separator=','> " +
            " #{s}" +
            " </foreach> </script>")
    List<BookCreateTxtErrorLog> findByStatus(Integer[] status);

    /**
     * 标记为处理成功
     * @param bookid 电子书id
     */
    @Update("UPDATE book_create_txt_error_log SET status = -1,lastmodify = CURRENT_TIMESTAMP WHERE bookid = #{bookid}")
    void markSuccess(Long bookid);

    /**
     * 批量忽略错误
     *
     * @param ids 错误日志id
     */
    @Update("<script> UPDATE book_create_txt_error_log SET status = 2,lastmodify = CURRENT_TIMESTAMP WHERE id in " +
            "<foreach collection='array' item = 'id' index='index' " +
            " open='(' close=')' separator=','> " +
            " #{id}" +
            " </foreach> </script>")
    void ignoreErrors(Long[] ids);

    /**
     * 判断电子书是否有生成txt错误
     *
     * @param bookId 电子书id
     */
    @Select("Select count(id) From book_create_txt_error_log Where bookid = #{bookId}")
    Integer countByBookid(Long bookId);

    @Select("Select * From book_create_txt_error_log Where id = #{id}")
    BookCreateTxtErrorLog findById(Long id);

    /**
     * 根据状态统计记录条数
     * @param status
     * @return
     */
    @Select("<script> Select count(id) From book_create_txt_error_log Where status in " +
            "<foreach collection='array' item = 's' index='index' " +
            " open='(' close=')' separator=','> " +
            " #{s} " +
            " </foreach> </script>")
    Long countByStatus(Integer[] status);
    @Insert("Insert Into book_create_txt_error_log (bookid,booktitle," +
            "lastmodify,errormessage,status,chapternum) " +
            "Values(#{bookid,jdbcType = BIGINT},#{booktitle,jdbcType = VARCHAR}," +
            "#{lastmodify},#{errormessage,jdbcType = VARCHAR}," +
            "#{status},#{chapternum,jdbcType = INTEGER})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void save(BookCreateTxtErrorLog errorLog);

    /**
     * 根据页码获取错误日志尚未处理列表
     * @return
     */
    @Select("<script> Select * From book_create_txt_error_log Where status in " +
            "<foreach collection='array' item='s' index='index' " +
            " open='(' close=')' separator=','> " +
            " #{s} " +
            " </foreach> </script>")
    List<BookCreateTxtErrorLog> getErrorListByStatus(Integer[] status);
}
