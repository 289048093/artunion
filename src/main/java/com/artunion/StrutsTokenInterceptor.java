/**
 * Copyright(c) 2012 ShenZhen CloudKing Technology Co., Ltd
 * All rights reserved.
 * Created on Oct 15, 2012  2:17:56 PM
 */

package com.artunion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.TokenHelper;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * Token拉按揭期
 * 
 * @author CloudKing
 * 
 */
public class StrutsTokenInterceptor extends MethodFilterInterceptor {

    /**
     * 无效返回的试图
     */
    public static final String INVALID_TOKEN_CODE = "invalid.token";

    /**
     * 
     */
    private static final long serialVersionUID = -6680894220590585506L;

    /**
     * @see com.opensymphony.xwork2.interceptor.MethodFilterInterceptor#doIntercept(com.opensymphony.xwork2.ActionInvocation)
     * @throws Exception
     *             所有异常
     */
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Intercepting invocation to check for valid transaction token.");
        }

        //see WW-2902: we need to use the real HttpSession here, as opposed to the map
        //that wraps the session, because a new wrap is created on every request
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession(true);
        synchronized (session) {
            //如果没有token参数，就不做token验证
            String res = "";
            if (TokenHelper.getTokenName() != null) {
                if (!TokenHelper.validToken()) {
                    res = handleInvalidToken(invocation);
//                    if (!StringUtil.isBlank(srcUrl)) {
//                        response.sendRedirect(srcUrl);
//                    }
                    return res;
                }
            }
            res = handleValidToken(invocation);
            return res;
        }
    }

    /**
     * Determines what to do if an invalid token is provided. If the action implements {@link ValidationAware}
     * 
     * @param invocation
     *            the action invocation where the invalid token failed
     * @return the return code to indicate should be processed
     * @throws Exception
     *             when any unexpected error occurs.
     */
    protected String handleInvalidToken(ActionInvocation invocation) throws Exception {
        BaseAction<?> action = (BaseAction<?>) invocation.getAction();
        action.getCloudContext().addErrorMsg("您已经提交过了，请勿重复提交表单");
//        String errorMessage = LocalizedTextUtil.findText(this.getClass(), "struts.messages.invalid.token", invocation
//                .getInvocationContext().getLocale(),
//                "数据重复提交，请返回后重试.", new Object[0]);
//
//        if (action instanceof ValidationAware) {
//            ((ValidationAware) action).addActionError(errorMessage);
//        } else {
//            log.warn(errorMessage);
//        }
        return invocation.invoke();
    }

    /**
     * Called when a valid token is found. This method invokes the action by can be changed to do something more
     * interesting.
     * 
     * @param invocation
     *            the action invocation
     * @throws Exception
     *             when any unexpected error occurs.
     */
    protected String handleValidToken(ActionInvocation invocation) throws Exception {
        return invocation.invoke();
    }
}
