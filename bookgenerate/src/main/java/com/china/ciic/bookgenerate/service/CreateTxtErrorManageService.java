package com.china.ciic.bookgenerate.service;


import com.china.ciic.bookgenerate.model.json.BaseResponse;

/**
 * 创建txt错误的管理
 * Created by kakasun on 2017/4/12.
 */
public interface CreateTxtErrorManageService {
    /**
     * 获取错误日志尚未处理列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    BaseResponse getErrorList(int pageNo, int pageSize);

    /**
     * 对电子书再次创建txt
     * @param bookids
     * @return
     */
    BaseResponse tryAginErrors(long[] bookids);

    /**
     * 忽略错误
     * @param ids 错误日志id
     * @return
     */
    BaseResponse ignorErrors(Long[] ids);

    /**
     * 将错误标记为处理成功
     * @param bookid 电子书id
     * @return
     */
    BaseResponse markSuccess(Long bookid);

    /**
     * 获取错误处理成功的列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    BaseResponse getSuccessList(int pageNo, int pageSize);

    /**
     * 获取忽略错误列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    BaseResponse getIgnorList(int pageNo, int pageSize);

    /**
     * 获取重试错误列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    BaseResponse getErrorAginList(int pageNo, int pageSize);
}
