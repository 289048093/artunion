/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Apr 27, 2013  5:53:10 PM
 */
package com.artunion.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.artunion.BaseEntity;

/**
 * 用户
 * 
 * @author LiZhao
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "tb_user")
public class UserEntity extends BaseEntity {
    /**
     * 
     */
    private static final long serialVersionUID = -4616405021606152401L;
    /**
     * 用户名
     */
    @Column(name = "USERNAME_", length = 20, nullable = false, unique = true)
    private String username;
    /**
     * 登陆密码
     */
    @Column(name = "PASSWORD_", columnDefinition = "CHAR(32)", length = 32, nullable = false)
    private String password;
    /**
     * 添加时间
     */
    @Column(name = "ADD_DATE_")
    private Date addDate;

    /**
     * 电子邮箱
     */
    @Column(name = "EMAIL_")
    private String email;
    /**
     * 手机
     */
    @Column(name = "MOBILEPHONE_")
    private String mobilePhone;
    /**
     * 电话
     */
    @Column(name = "TELPHONE_")
    private String telPhone;
    /**
     * 性别
     */
    @Column(name = "SEX_", length = 2)
    private String sex;

    /**
     * 真实姓名
     */
    @Column(name = "REALNAME_", length = 20, nullable = false)
    private String realname;
    /**
     * 头像
     */
    @Column(name = "HEAD_PIC_")
    private String headPic;
    /**
     * 地址
     */
    @Column(name = "ADDR_", length = 200)
    private String addr;

    /**
     * 最后一次登陆时间
     */
    @Column(name = "LAST_LOGIN_DATE_")
    private Date lastLoginDate;
    /**
     * 状态
     */
    @Column(name = "STATUS_", nullable = false)
    private Byte status;

    /**
     * 角色
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tb_user_bid_roles", joinColumns = { @JoinColumn(name = "USER_ID_", referencedColumnName = "ID_") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID_", referencedColumnName = "ID_") })
    private List<RoleEntity> roles;

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

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

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
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

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

}