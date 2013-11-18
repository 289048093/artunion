/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Apr 28, 2013  10:58:25 AM
 */
package com.artunion.vo;

import com.artunion.BaseVO;

/**
 * 菜单
 * 
 * @author CloudKing
 */
@SuppressWarnings("serial")
public class MenuVO extends BaseVO {

    /**
     * 菜单名称
     */
    private String name;
    /**
     * 描述
     */
    private String desc;
    /**
     * 菜单默认url
     */
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
