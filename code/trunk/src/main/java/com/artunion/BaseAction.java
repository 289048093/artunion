/**
 * Copyright(c) 2012 ShenZhen CloudKing Technology Co., Ltd
 * All rights reserved.
 * Created on Oct 15, 2012  2:17:56 PM
 */
package com.artunion;

import java.lang.reflect.ParameterizedType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.artunion.util.Constant;
import com.artunion.util.LogUtil;
import com.artunion.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 基本的Action，包括CloudContent的注入
 * 
 * @author CloudKing
 * @param <T>
 *            BaseVO的子类
 */
@SuppressWarnings("unchecked")
public abstract class BaseAction<T extends BaseVO> extends ActionSupport {
    /**
     * 
     */
    private static final long serialVersionUID = -5116005823652933077L;
    /**
     * 成功的标识
     */
    protected static final String SUCCESS = "success";
    /**
     * 跳转页面
     */
    protected static final String JUMP = "jump";
    /**
     * input
     */
    protected static final String INPUT = "input";
    /**
     * 初始化标识
     */
    protected static final String INIT = "init";
    /**
     * JSON标识
     */
    protected static final String JSON = "json";
    /**
     * 页面标识
     */
    protected static final String PAGE = "page";
    /**
     * 文件上传标识
     */
    protected static final String FILEUPLOAD = "fileUpload";
    /**
     * 到处excel标识
     */
    protected static final String OUTEXCEL = "outexcel";
    /**
     * 下载标识
     */
    protected static final String DOWNLOAD = "download";

    /**
     * ArtunionContext上下文，用来存放所有请求和响应的容器
     */
    protected ArtUnionContext<T> artunionContext = new ArtUnionContext<T>();

    /**
     * 构造方法
     */
    public BaseAction(){
        try {
            ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
            if (parameterizedType != null) {
                Class<T> clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
                artunionContext.setVo(clazz.newInstance());

            }
            //设置页数
            int start = StringUtil.isBlank(getRequest().getParameter("pager.offset"))
                            || Integer.parseInt(getRequest().getParameter("pager.offset")) < 0 ? artunionContext
                            .getPageInfo().getStart() : Integer.parseInt(getRequest().getParameter("pager.offset"));
            if(StringUtil.isBlank(getRequest().getParameter("pagesize"))){
                artunionContext.addParam("pagesize_null_flag", true);
            }
            int pagesize = StringUtil.isBlank(getRequest().getParameter("pagesize")) ? artunionContext.getPageInfo()
                            .getEachPageData() : Integer.parseInt(getRequest().getParameter("pagesize"));
            artunionContext.getPageInfo().setNowPage((start + pagesize) / pagesize);
            artunionContext.getPageInfo().setEachPageData(pagesize);
            artunionContext.setLoginedUser(getLoginedUser());
        } catch (Exception e) {
            LogUtil.error(e);
        }
    }

    /**
     * 获取request
     */
    protected HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    /**
     * 获取session
     */
    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取response
     */
    protected HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    /**
     * 获取验证码
     * 
     * @return
     */
    protected String getVerifyCode() {
        return (String) getAttrBySession(Constant.VERIFY_CODE);
    }

    /**
     * 存放用户
     * 
     * @param name
     * @param value
     */
    protected void putLoginedUser(LoginedUser loginedUser) {
        setAttrBySession(Constant.LOGINED_USER, loginedUser);
    }

    /**
     * 获取用户
     * 
     * @param name
     * @param value
     */
    protected LoginedUser getLoginedUser() {
        return (LoginedUser) getAttrBySession(Constant.LOGINED_USER);
    }

    /**
     * 把数据存放在request里面
     * 
     * @param name
     * @param value
     */
    protected void setAttrByRequest(String key, Object value) {
        ServletActionContext.getRequest().setAttribute(key, value);
    }

    /**
     * 把数据存放在session里面
     * 
     * @param name
     * @param value
     */
    protected void setAttrBySession(String key, Object value) {
        ServletActionContext.getRequest().getSession().setAttribute(key, value);
    }

    /**
     * 
     * @param name
     * @param value
     */
    protected Object getAttrByRequest(String name) {
        return ServletActionContext.getRequest().getAttribute(name);
    }

    /**
     * 从session取出数据
     * 
     * @param name
     * @param value
     */
    protected Object getAttrBySession(String name) {
        return ServletActionContext.getRequest().getSession().getAttribute(name);
    }

    public ArtUnionContext<T> getArtunionContext() {
        return artunionContext;
    }

}
