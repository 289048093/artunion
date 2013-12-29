/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  May 6, 2013  1:50:39 PM
 */
package com.artunion.vo;

import java.util.Date;
import java.util.List;

import com.artunion.BaseVO;
import com.artunion.entity.UserEntity;

/**
 * @author CloudKing
 */
public class RoleVO extends BaseVO {

    /**
     * 
     */
    private static final long serialVersionUID = 978604023925645073L;

    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String desc;
    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 用户
     */
    private List<UserEntity> users;

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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

}
