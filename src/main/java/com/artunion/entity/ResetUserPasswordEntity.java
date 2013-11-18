/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Oct 17, 2013  3:23:37 PM
 */
package com.artunion.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.artunion.BaseEntity;

/**
 * 重置密码实体类
 * @author CloudKing
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tb_reset_user_password")
public class ResetUserPasswordEntity extends BaseEntity {

    @Column(name = "KEY_")
    private String key;

    @Column(name = "ADD_DATE_")
    private Date addDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID_")
    private UserEntity user;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
