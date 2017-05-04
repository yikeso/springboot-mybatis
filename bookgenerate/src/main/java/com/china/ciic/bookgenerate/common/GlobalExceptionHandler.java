package com.china.ciic.bookgenerate.common;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by kakasun on 2017/5/3
 */
@EnableWebMvc
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String defaultExceptionHandler(HttpRequest request,Exception e){
        return "系统异常，稍后再试";
    }

}
