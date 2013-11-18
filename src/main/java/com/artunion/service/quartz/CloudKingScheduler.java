/**
 * Copyright(c) 2012 ShenZhen CloudKing Technology Co., Ltd
 * All rights reserved.
 * Created on Oct 15, 2012  2:17:56 PM
 */
package com.artunion.service.quartz;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.artunion.PropertyManager;
import com.artunion.dao.ResetUserPasswordDAO;
import com.artunion.entity.ResetUserPasswordEntity;
import com.artunion.util.LogUtil;

/**
 * @author CloudKing 任务调度器
 */
public class CloudKingScheduler {

    @Resource
    private ResetUserPasswordDAO resetUserPasswordDAO;

    /**
     * 自定义任务
     * 
     * @throws Exception
     *             所有异常
     */
    public void quartzCkDatabaseBackUpTask() throws Exception {
    }

    /**
     * 每日任务
     * 
     * @throws Exception
     *             所有异常
     */
    public void quartzEveryDayTask() throws Exception {
    }

    /**
     * 每分钟任务
     */
    public void quartzEveryMinuteTask() {
        try {
            checkResetUserPassword();
        } catch (Exception e) {
            LogUtil.error(e);
        }
    }

    /**
     * 检查重置密码的邮件是否失效
     * 
     * @throws SQLException
     */
    private void checkResetUserPassword() throws SQLException {
        if("".equals(""))return;
        List<ResetUserPasswordEntity> rupes = resetUserPasswordDAO.list();
        int invalidTime = Integer.parseInt(PropertyManager.getInstance().getDbProperty(
                        PropertyManager.DB_RESET_USER_PASSWORD_EMAIL_INVALID_TIME));
        Date now = null;
        Date date = null;
        for (ResetUserPasswordEntity e : rupes) {
            now = new Date();
            date = e.getAddDate();
            if (now.getTime() - date.getTime() >= 1000 * 60 * 60 * invalidTime) {
                resetUserPasswordDAO.delete(e);
            }
        }
    }
}
