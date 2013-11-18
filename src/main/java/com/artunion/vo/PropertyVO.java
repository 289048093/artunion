/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Apr 28, 2013  10:58:25 AM
 */
package com.artunion.vo;

import com.artunion.BaseVO;

/**
 * 属性
 * 
 * @author CloudKing
 * 
 */
@SuppressWarnings("serial")
public class PropertyVO extends BaseVO {
    /**
     * 基本信息
     */
    /**
     * key
     */
    private String key;
    /**
     * 值
     */
    private String value;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 描述
     */
    private String desc;

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
