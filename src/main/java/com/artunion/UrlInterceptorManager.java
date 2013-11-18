/**
 * Copyright(c) 2012 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Nov 14, 2012  3:14:34 PM
 */
package com.artunion;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * url管理器，包括地址拦截
 * 
 * @author CloudKing
 * 
 */
@SuppressWarnings("unchecked")
public class UrlInterceptorManager {
    /**
     * 解析xml属性
     */
    private static Map<String, Object> parsedXMLProperty = new ConcurrentHashMap<String, Object>();
    /**
     * 实例对象
     */
    private static UrlInterceptorManager instance = new UrlInterceptorManager();

    /**
     * 获取实例对象
     * 
     * @return
     */
    public static UrlInterceptorManager getInstance() {
        return instance;
    }

    /**
     * 获取没有的权限url
     * 
     * @return
     */
    public Set<String> getExcludeAuthUrls() {
        return (Set<String>) parsedXMLProperty.get("parsedExcludeAuthUrls");
    }

    /**
     * 判断是否存在
     * 
     * @param url
     * @param exclude
     * @return
     */
    public boolean testURLPassesExclude(String url) {
        url = url.toLowerCase();
        for (String path : getExcludeAuthUrls()) {
            if (url.matches(path)) {
                return true;
            }
        }
        return false;

    }

    /**
     * 初始化没有的地址
     * 
     * @param excludeAuthUrls
     */
    public void initExcludeAuthUrls() {
        String excludeAuthUrls = PropertyManager.getInstance().getXMLProperty(
                        PropertyManager.XML_ARTUNION_EXCLUDEAUTHURLS);
        Set<String> parsedExclude = new HashSet<String>();
        if (excludeAuthUrls.length() != 0) {
            String[] tmps = null;
            if (excludeAuthUrls.equals("")) {
                tmps = new String[0];
            } else {
                tmps = excludeAuthUrls.split(",");
            }
            for (String tmp : tmps) {
                parsedExclude.add("^"
                                + tmp.replaceAll("\\*", ".*").replaceAll("\\$", "\\\\$").replaceAll("\\?", "\\\\?")
                                                .replaceAll("\\.", "\\\\.").toLowerCase());
            }
            parsedXMLProperty.put("parsedExcludeAuthUrls", parsedExclude);
        } else {
            parsedXMLProperty.put("parsedExcludeAuthUrls", parsedExclude);
        }
    }
}
