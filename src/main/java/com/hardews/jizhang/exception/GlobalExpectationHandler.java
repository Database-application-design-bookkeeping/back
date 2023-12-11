package com.hardews.jizhang.exception;


import com.hardews.jizhang.utils.R;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.hardews.jizhang.controller")
public class GlobalExpectationHandler {

    @ExceptionHandler(Exception.class)
    public R expectation(Exception e){
        Logger logger = LogManager.getLogger();
        logger.error("Expectation", e);
        return R.error();
    }
}
