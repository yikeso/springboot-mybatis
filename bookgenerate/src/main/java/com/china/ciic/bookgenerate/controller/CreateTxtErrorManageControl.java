package com.china.ciic.bookgenerate.controller;

import com.china.ciic.bookgenerate.model.json.BaseResponse;
import com.china.ciic.bookgenerate.service.CreateTxtErrorManageService;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 创建txt错误，的管理
 * Created by kakasun on 2017/4/12.
 */
@RestController
@RequestMapping("/errormanage")
public class CreateTxtErrorManageControl {
    @Resource
    CreateTxtErrorManageService errorManageService;
    @Resource
    Environment setSource;

    /**
     * 获取还没处理的错误列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("/errorlistwithoutdeal")
    public BaseResponse errorListWithoutdeal(Integer pageNo, Integer pageSize){
        if(pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        if (pageSize == null){
            String pageSizeStr = setSource.getProperty("pageSize","10");
            try {
                pageSize = Integer.parseInt(pageSizeStr.trim());
            }catch (Exception e){
                pageSize = 10;
            }
        }
        return errorManageService.getErrorList(pageNo,pageSize);
    }

    /**
     * 再次创建txt
     * @param bookids
     * @return
     */
    @RequestMapping("/tryaginerror")
    public BaseResponse tryAginError(long[] bookids){
        BaseResponse result = null;
        if(bookids == null){
            result = new BaseResponse();
            result.setStatus(1);
            result.setMessage("电子书的id不得为空");
            return result;
        }
        result = errorManageService.tryAginErrors(bookids);
        return result;
    }

    /**
     * 忽略错误
     * @param ids
     * @return
     */
    @RequestMapping("/ignorerrors")
    public BaseResponse ignoreErrors(Long[] ids){
        BaseResponse result = null;
        if(ids == null){
            result = new BaseResponse();
            result.setStatus(1);
            result.setMessage("日志的id不得为空");
            return result;
        }
        result = errorManageService.ignorErrors(ids);
        return result;
    }

    /**
     * 将错误日志标记为处理成功
     * @param bookid
     * @return
     */
    @RequestMapping("/marksuccess")
    public BaseResponse markSuccess(Long bookid){
        BaseResponse result = null;
        if(bookid == null){
            result = new BaseResponse();
            result.setStatus(1);
            result.setMessage("电子书的id不得为空");
            return result;
        }
        result = errorManageService.markSuccess(bookid);
        return result;
    }

    /**
     * 错误处理成功列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("/successlist")
    public BaseResponse successList(Integer pageNo,Integer pageSize){
        if(pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        if (pageSize == null){
            String pageSizeStr = setSource.getProperty("pageSize","10");
            try {
                pageSize = Integer.parseInt(pageSizeStr.trim());
            }catch (Exception e){
                pageSize = 10;
            }
        }
        return errorManageService.getSuccessList(pageNo,pageSize);
    }
    /**
     * 错误忽略列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("/ignorelist")
    public BaseResponse ignoreList(Integer pageNo,Integer pageSize){
        if(pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        if (pageSize == null){
            String pageSizeStr = setSource.getProperty("pageSize","10");
            try {
                pageSize = Integer.parseInt(pageSizeStr.trim());
            }catch (Exception e){
                pageSize = 10;
            }
        }
        return errorManageService.getIgnorList(pageNo,pageSize);
    }

    /**
     * 重试失败列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping("/aginlist")
    public BaseResponse aginList(Integer pageNo,Integer pageSize){
        if(pageNo == null || pageNo < 1){
            pageNo = 1;
        }
        if (pageSize == null){
            String pageSizeStr = setSource.getProperty("pageSize","10");
            try {
                pageSize = Integer.parseInt(pageSizeStr.trim());
            }catch (Exception e){
                pageSize = 10;
            }
        }
        return errorManageService.getErrorAginList(pageNo,pageSize);
    }
}
