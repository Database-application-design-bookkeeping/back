package com.hardews.jizhang.exception;


import com.hardews.jizhang.utils.R;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.hardews.jizhang.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R exception(Exception e){
        Logger logger = LogManager.getLogger();
        logger.error("Exception", e);
        return R.error();
    }
}
