package com.DigitalTwin.project.common;

/**
 * @author: ZhuangShuo
 * @date: 2023/4/13 17:59
 * @description: 自定义业务异常类
 */
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }


}
