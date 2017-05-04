package com.china.ciic.bookgenerate.service.impl;

import com.china.ciic.bookgenerate.dao.entity.BookCreateTxtErrorLog;
import com.china.ciic.bookgenerate.model.json.BaseResponse;
import com.china.ciic.bookgenerate.model.json.PageResult;
import com.china.ciic.bookgenerate.service.CreateTxtErrorManageService;
import com.china.ciic.bookgenerate.task.BookTxtCreate;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建txt错误的管理
 * Created by kakasun on 2017/4/12.
 */
@Service
public class CreateTxtErrorManageServiceImpl implements CreateTxtErrorManageService {
    //线程池。
    public final static ExecutorService pool;
    @Resource
    BookCreateTxtErrorLogServiceImpl logRepository;
    @Resource
    BookTxtCreate txtCreate;

    static {
        pool = Executors.newSingleThreadExecutor();
    }

    /**
     * 启动项目是将之前为重试完成的任务添加到线程池中继续重试
     */
    @PostConstruct
    public void init(){
        List<BookCreateTxtErrorLog> list = logRepository.findByStatus(new Integer[]{3});
        if(list != null && list.size() > 0){
            for(BookCreateTxtErrorLog log:list){
                pool.execute(new MakeTxtAgin(log.getId(),txtCreate));
            }
        }
    }

    /**
     * 获取尚未处理错误列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public BaseResponse getErrorList(int pageNo, int pageSize) {
        PageResult<BookCreateTxtErrorLog> result = new PageResult<BookCreateTxtErrorLog>();
        PageHelper.startPage(pageNo,pageSize);
        result.setList(logRepository.getErrorListByStatus(new Integer[]{0}));
        result.setPageNo(pageNo);
        result.setTotal(logRepository.countByStatus(new Integer[]{0}));
        return result;
    }

    /**
     * 将电子书再次创建txt
     * @param bookids
     * @return
     */
    @Override
    public BaseResponse tryAginErrors(long[] bookids) {
        for (long id : bookids){
            //标记为正在重试中
            logRepository.tryAngining(id);
            //将任务添加到线程池
            pool.execute(new MakeTxtAgin(id,txtCreate));
        }
        return new BaseResponse();
    }

    /**
     * 忽略错误
     * @param ids 错误日志id
     * @return
     */
    @Override
    public BaseResponse ignorErrors(Long[] ids) {
        logRepository.ignoreErrors(ids);
        return new BaseResponse();
    }

    /**
     * 将错误标记为处理成功
     * @param bookid 电子书id
     * @return
     */
    @Override
    public BaseResponse markSuccess(Long bookid) {
        logRepository.markSuccess(bookid);
        return new BaseResponse();
    }

    @Override
    public BaseResponse getSuccessList(int pageNo, int pageSize) {
        PageResult<BookCreateTxtErrorLog> result = new PageResult<BookCreateTxtErrorLog>();
        PageHelper.startPage(pageNo,pageSize);
        result.setList(logRepository.getErrorListByStatus(new Integer[]{-1}));
        result.setPageNo(pageNo);
        result.setTotal(logRepository.countByStatus(new Integer[]{-1}));
        return result;
    }

    @Override
    public BaseResponse getIgnorList(int pageNo, int pageSize) {
        PageResult<BookCreateTxtErrorLog> result = new PageResult<BookCreateTxtErrorLog>();
        PageHelper.startPage(pageNo,pageSize);
        result.setList(logRepository.getErrorListByStatus(new Integer[]{2}));
        result.setPageNo(pageNo);
        result.setTotal(logRepository.countByStatus(new Integer[]{2}));
        return result;
    }

    @Override
    public BaseResponse getErrorAginList(int pageNo, int pageSize) {
        PageResult<BookCreateTxtErrorLog> result = new PageResult<BookCreateTxtErrorLog>();
        PageHelper.startPage(pageNo,pageSize);
        result.setList(logRepository.getErrorListByStatus(new Integer[]{1}));
        result.setPageNo(pageNo);
        result.setTotal(logRepository.countByStatus(new Integer[]{1}));
        return result;
    }


    /**
     * 再次创建txt
     */
    class MakeTxtAgin implements Runnable{

        Long bookId;
        BookTxtCreate bookTxtCreate;
        public MakeTxtAgin(Long bookId,BookTxtCreate bookTxtCreate){
            this.bookId = bookId;
            this.bookTxtCreate = bookTxtCreate;
        }

        @Override
        public void run() {
            bookTxtCreate.makeTxtAgineByBookId(bookId);
        }
    }
}
