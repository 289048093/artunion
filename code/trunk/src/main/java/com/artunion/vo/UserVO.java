/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Apr 28, 2013  10:58:25 AM
 */
package com.artunion.vo;

import java.util.Date;

import com.artunion.BaseVO;

/**
 * 用户
 * 
 * @author LiZhao
 */
public class UserVO extends BaseVO {
    /**
     * 
     */
    private static final long serialVersionUID = 4064870001294136785L;
    /**
     * 基本信息
     */
    /**
     * 用户名
     */
    private String username;
    /**
     * 所在单位名称
     */
    /**
     * 真实姓名
     */
    private String realname;
    /**
     * 头像
     */
    private String headPic;
    /**
     * 登陆密码
     */
    private String password;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 手机
     */
    private String mobilePhone;
    /**
     * 电话
     */
    private String telPhone;
    /**
     * 性别
     */
    private String sex;
    /**
     * 添加时间
     */
    private Date addDate;
    /**
     * 最后一次登陆时间
     */
    private Date lastLoginDate;
    /**
     * 地址
     */
    private String addr;
    /**
     * 状态
     */
    private String status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

}
