package com.DigitalTwin.project.common;

/**
 * @author: ZhuangShuo
 * @date: 2023/4/12 22:35
 * @description: 基于ThreadLocal封装工具类，用来保存和获取当前登录用户id
 */
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置值
     *
     * @param id
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }


    /**
     * 获取值
     *
     * @return
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }


}
