package com.DigitalTwin.project.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author: ZhuangShuo
 * @date: 2023/4/12 18:32
 * @description: 全局异常处理
 */

@ControllerAdvice(annotations = {RestController.class, Controller.class})//只要类上有RestController、Controller就会被处理
@ResponseBody//封装json数据并返回
@Slf4j
public class GlobalExceptionHandler {

    /**
     * SQLIntegrityConstraintViolationException 异常处理方法
     *
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        log.error(ex.getMessage());

        if (ex.getMessage().contains("Duplicate entry")) {
            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return R.error(msg);
        }


        return R.error("未知错误");
    }

    /**
     * CustomException 异常处理方法
     *
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException ex) {
        log.error(ex.getMessage());


        return R.error(ex.getMessage());
    }

}
