/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Apr 1, 2013  11:56:24 AM
 */
package com.artunion.vo;

import com.artunion.BaseVO;

/**
 * @author CloudKing
 */
public class RightsVO extends BaseVO {

    /**
     * 
     */
    private static final long serialVersionUID = -638985199583606619L;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 请求地址
     */
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
