/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Apr 27, 2013  5:53:10 PM
 */
package com.artunion.util;

import org.apache.log4j.Logger;

/**
 * 日志工具类
 * 
 * @author xgj
 * 
 * May 26, 2010
 */
public final class LogUtil {
    /**
     * 私有化构造器
     */
    private LogUtil(){

    }

    /**
     * 获取InfoLogger
     * 
     * @return
     */
    public static Logger getInfoLogger() {
        return Logger.getLogger("artunionInfoLogger");
    }

    /**
     * 获取WarnLogger
     * 
     * @return
     */
    public static Logger getWarnLogger() {
        return Logger.getLogger("artunionWarnLogger");
    }

    /**
     * 获取ErrorLogger
     * 
     * @return
     */
    public static Logger getErrorLogger() {
        return Logger.getLogger("artunionErrorLogger");
    }

    /**
     * 获取DebugLogger
     * 
     * @return
     */
    public static Logger getDebugLogger() {
        return Logger.getLogger("artunionDebugLogger");
    }

    /**
     * 获取FatalLogger
     * 
     * @return
     */
    public static Logger getFatalLogger() {
        return Logger.getLogger("artunionFatalLogger");
    }

    /**
     * error log
     * 
     * @param message
     */
    public static void error(String message) {
        getErrorLogger().error(message);
    }

    /**
     * error log
     * 
     * @param throwable
     */
    public static void error(Throwable throwable) {
        getErrorLogger().error("", throwable);
    }

    /**
     * error log
     * 
     * @param throwable
     */
    public static void error(String message, Throwable throwable) {
        getErrorLogger().error(message, throwable);
    }

    /**
     * debug log
     * 
     * @param throwable
     */
    public static void debug(Throwable throwable) {
        getDebugLogger().debug("", throwable);
    }

    /**
     * debug log
     * 
     * @param message
     */
    public static void debug(String message) {
        getDebugLogger().debug(message);
    }

    /**
     * debug log
     * 
     * @param message
     * @param throwable
     */
    public static void debug(String message, Throwable throwable) {
        getDebugLogger().debug(message, throwable);
    }

    /**
     * info log
     * 
     * @param throwable
     */
    public static void info(Throwable throwable) {
        getInfoLogger().info("", throwable);
    }

    /**
     * info log
     * 
     * @param message
     */
    public static void info(String message) {
        getInfoLogger().info(message);
    }

    /**
     * info log
     * 
     * @param message
     * @param throwable
     */
    public static void info(String message, Throwable throwable) {
        getInfoLogger().info(message, throwable);
    }

    /**
     * fatal log
     * 
     * @param message
     */
    public static void fatal(String message) {
        getFatalLogger().fatal(message);
    }

    /**
     * fatal log
     * 
     * @param throwable
     */
    public static void fatal(Throwable throwable) {
        getFatalLogger().fatal("", throwable);
    }

    /**
     * fatal log
     * 
     * @param message
     * @param throwable
     */
    public static void fatal(String message, Throwable throwable) {
        getFatalLogger().fatal(message, throwable);
    }

    /**
     * warn log
     * 
     * @param message
     */
    public static void warn(String message) {
        getWarnLogger().warn(message);
    }

    /**
     * warn log
     * 
     * @param throwable
     */
    public static void warn(Throwable throwable) {
        getFatalLogger().warn(null, throwable);
    }

    /**
     * warn log
     * 
     * @param message
     * @param throwable
     */
    public static void warn(String message, Throwable throwable) {
        getFatalLogger().warn(message, throwable);
    }

    public static boolean isDebugEnabled() {
        return Logger.getRootLogger().isDebugEnabled();
    }

    public static boolean isTraceEnabled() {
        return Logger.getRootLogger().isTraceEnabled();
    }

    public static boolean isInfoEnabled() {
        return Logger.getRootLogger().isInfoEnabled();
    }

}
