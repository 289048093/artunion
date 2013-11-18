/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Apr 27, 2013  5:53:10 PM
 */
package com.artunion.util;

import java.io.File;
import java.net.URL;

/**
 * 得到系统环境工具
 * 
 * @author xgj
 * 
 */
public final class ContextUtil {
    /**
     * 构造器私有化
     */
    private ContextUtil(){
        // TODO Auto-generated constructor stub
    }

    /**
     * 得到根目录
     * 
     * @return
     */
    public static String getRootPath() {
        String s = getClassesPath();
        return s.split("WEB-INF")[0];
    }

    /**
     * 得到classes目录
     * 
     * @return
     */
    public static String getClassesPath() {
        URL url = Thread.currentThread().getContextClassLoader().getResource("");
        if (url == null) {
            url = ContextUtil.class.getClassLoader().getResource("");
        }
        if (url != null) {
            return url.getFile();
        } else {
            return null;
        }
    }

    /**
     * 得到临时目录
     * 
     * @return
     */
    public static String getTmpPath() {
        String tmp = System.getProperty("java.io.tmpdir");
        if (tmp == null) {
            tmp = getRootPath() + File.separator + "tmp";
            File file = new File(tmp);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        return tmp;
    }
}
