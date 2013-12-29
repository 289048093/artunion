/**
 * Copyright(c) 2012 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Nov 14, 2012  3:14:34 PM
 */
package com.artunion;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

/**
 * 
 * @author CloudKing
 */
@SuppressWarnings("unchecked")
public class StrutsBooleanConverter extends StrutsTypeConverter {
    /**
     * form数据转换
     */
    public Object convertFromString(Map context, String[] values, Class toClass) {
        if (values[0] == null || values[0].trim().equals("")) {
            return null;
        }
        return Boolean.parseBoolean(values[0]);
    }

    /**
     * form数据转换
     */
    public String convertToString(Map context, Object o) {
        return o.toString();
    }
}
