/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Apr 27, 2013  5:53:10 PM
 */
package com.artunion.util;

/**
 * 常量
 * 
 * @author xgj
 * 
 */
public interface Constant {
    /**
     * 登陆用户的信息
     */
    String LOGINED_USER = "userLogin";
    /**
     * 验证码
     */
    String VERIFY_CODE = "verifyCode";
    /**
     * 用户正常状态
     */
    Byte USER_STATE_NORMAL = 1;
    /**
     * 用户删除状态
     */
    Byte USER_STATE_DELETE = 0;
    /**
     * 角色持久化 是
     */
    String ROLE_PERSISTENCE_YES = "1";
    /**
     * 角色持久化 否
     */
    String ROLE_PERSISTENCE_NO = "0";

    /**
     * 管理员角色
     */
    Long ROLE_MANAGER_ID = 1L;
    /**
     * 普通用户角色
     */
    Long ROLE_COMMON_USER_ID = 3L;
}
