package com.cj.mybatis.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cj.mybatis.util.ResponseUtil;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	
	@ExceptionHandler(Throwable.class)
    public ResponseUtil onException(Throwable ex,HttpServletRequest request){
		LOGGER.error("url: {}, msg: {}", request.getRequestURL(), ex.getMessage());
        return ResponseUtil.fail("get exception:"+ex.getMessage());
    }
}
