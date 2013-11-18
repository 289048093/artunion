/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Apr 1, 2013  11:56:24 AM
 */
package com.artunion.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.artunion.BaseEntity;

/**权限
 * @author CloudKing
 */
@Entity
@Table(name = "tb_rights")
public class RightsEntity extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = -638985199583606619L;
    /**
     * 权限名称
     */
    @Column(name = "NAME_")
    private String name;
    /**
     * 请求地址
     */
    @Column(name="URL_")
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
