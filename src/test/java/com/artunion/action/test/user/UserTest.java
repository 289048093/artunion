package com.artunion.action.test.user;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.artunion.dao.UserDAO;


@RunWith(SpringJUnit4ClassRunner.class) // 用于配置spring中测试的环境 
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class ,TransactionalTestExecutionListener.class})//监听
@ContextConfiguration(locations={"classpath:/applicationContext.xml"})//用于指定配置文件所在的位置 
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback =true)
public class UserTest {
    @Resource
    UserDAO userDAO;
    @BeforeClass
    public static void beforeClass() {}
    
    @Test
    public void insertTest() throws SQLException{
    }
    @Test
    public void deleteExpertTest() throws SQLException{
    }
    @AfterClass
    public static void afterClass(){}
}
