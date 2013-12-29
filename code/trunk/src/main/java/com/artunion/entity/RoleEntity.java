/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Apr 1, 2013  10:08:30 AM
 */
package com.artunion.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.artunion.BaseEntity;

/**
 * 角色
 * 
 * @author Lizhao
 */
@Entity
@Table(name = "tb_role")
public class RoleEntity extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 5502139406123698128L;
    /**
     * 名称
     */
    @Column(name = "NAME_", length = 30, nullable = false)
    private String name;
    /**
     * 描述
     */
    @Column(name = "DESC_", length = 255)
    private String desc;
    /**
     * 添加时间
     */
    @Column(name = "ADD_TIME_", nullable = false)
    private Date addTime;

    /**
     * 是否是内置角色，内置角色不能删除
     */
    @Column(name = "PERSISTENCE_")
    private Boolean persistence;
    /**
     * 用户
     */
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private List<UserEntity> users;

    public Boolean getPersistence() {
        return persistence;
    }

    public void setPersistence(Boolean persistence) {
        this.persistence = persistence;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

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

}
