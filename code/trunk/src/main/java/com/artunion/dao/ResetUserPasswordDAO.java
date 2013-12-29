/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Oct 17, 2013  3:57:16 PM
 */
package com.artunion.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.artunion.BaseDAO;
import com.artunion.entity.ResetUserPasswordEntity;

/**
 * 重置密码dao
 * @author CloudKing
 */
@Repository("resetUserPasswordDAO")
public class ResetUserPasswordDAO extends BaseDAO<ResetUserPasswordEntity> {

    /**
     * 
     * @param key
     * @return
     * @throws SQLException
     */
    public ResetUserPasswordEntity findByKey(String key) throws SQLException {
        return uniqueResult("select rup_ from ResetUserPasswordEntity rup_ where rup_.key=:key", "key", key);
    }

}
