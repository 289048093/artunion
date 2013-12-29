/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Apr 1, 2013  11:57:53 AM
 */
package com.artunion.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.artunion.BaseEntity;

/**
 * 菜单
 * 
 * @author LiZhao
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tb_menu")
public class MenuEntity extends BaseEntity {
    /**
     * 菜单名
     */
    @Column(name = "NAME_")
    private String name;
    /**
     * 描述
     */
    @Column(name = "DESC_")
    private String desc;
    /**
     * 菜单默认地址
     */
    @Column(name = "URL_")
    private String url;
    /**
     * 子菜单
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentMenu")
    private List<MenuEntity> childrenMenus;
    /**
     * 父菜单
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_MENU_ID_")
    private MenuEntity parentMenu;

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

    public List<MenuEntity> getChildrenMenus() {
        return childrenMenus;
    }

    public void setChildrenMenus(List<MenuEntity> childrenMenus) {
        this.childrenMenus = childrenMenus;
    }

    public MenuEntity getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(MenuEntity parentMenu) {
        this.parentMenu = parentMenu;
    }
}
