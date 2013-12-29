/**
 * 
 */
package com.artunion.entity;

import com.artunion.BaseEntity;

/**
 * 机构类型
 * 
 * @author LiZhao
 * 
 */
public class OrgCatEntity extends BaseEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 4841909976078585532L;

    /**
     * 机构类型名称
     */
    private String name;

    /**
     * 描述
     */
    private String desc;

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

}
