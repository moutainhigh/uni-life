package com.uni.life.crud.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 一些操作信息的帮助工具类
 *
 */
public class UserUtils {

    private static final Logger log = LoggerFactory.getLogger(UserUtils.class);

    /**
     * 获取当前登录的用户
     *
     * @return
     */
    public static String getCurrentUser() {
        String principal = "";
        try {
//            principal = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception e) {
            log.error("获取spring security的用户失败，原因={}", e.getMessage());
        }
        return principal;
    }

}
