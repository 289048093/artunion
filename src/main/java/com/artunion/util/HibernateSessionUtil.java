/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Apr 27, 2013  5:53:10 PM
 */
package com.artunion.util;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate session生成类
 * 
 * @author CloudKing
 * 
 */
public final class HibernateSessionUtil {
    /**
     * hibernate文件地址
     */
    private static final String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";
    /**
     * 线程池
     */
    private static final ThreadLocal<Session> THREADLOCAL = new ThreadLocal<Session>();
    /**
     * AnnotationConfiguration
     */
    private static Configuration configuration = new AnnotationConfiguration();
    /**
     * sessionFactory
     */
    private static org.hibernate.SessionFactory sessionFactory;
    /**
     * 配置文件地址
     */
    private static String configFile = CONFIG_FILE_LOCATION;
    static {
        try {
            configuration.configure(configFile);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            LogUtil.error(e);
        }
    }

    /**
     * 构造器私有化
     */
    private HibernateSessionUtil(){
    }

    /**
     * 获取session
     * 
     * @return
     */
    public static Session getSession() {
        Session session = (Session) THREADLOCAL.get();

        if (session == null || !session.isOpen()) {
            if (sessionFactory == null) {
                rebuildSessionFactory();
            }
            session = (sessionFactory != null) ? sessionFactory.openSession() : null;
            THREADLOCAL.set(session);
        }

        return session;
    }

    /**
     * 重建sessionFac
     */
    public static void rebuildSessionFactory() {
        try {
            configuration.configure(configFile);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Exception e) {
            LogUtil.error(e);
        }
    }

    /**
     * 关闭session
     * 
     */
    public static void closeSession() {
        Session session = (Session) THREADLOCAL.get();
        THREADLOCAL.set(null);
        if (session != null) {
            session.close();
        }
    }

    /**
     * 获取sessionfac
     * 
     * @return
     */
    public static org.hibernate.SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * 配置
     * 
     * @param configFile
     */
    public static void setConfigFile(String configFile) {
        HibernateSessionUtil.configFile = configFile;
        sessionFactory = null;
    }

    /**
     * 获取配置
     * 
     * @return
     */
    public static Configuration getConfiguration() {
        return configuration;
    }

}