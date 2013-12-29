/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Apr 28, 2013  10:58:25 AM
 */
package com.artunion.action.user;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.artunion.BaseAction;
import com.artunion.LoginedUser;
import com.artunion.service.user.UserService;
import com.artunion.util.Constant;
import com.artunion.util.ProjectUtil;
import com.artunion.util.StringUtil;
import com.artunion.vo.UserVO;

/**
 * 用户
 * 
 * @author CloudKing
 */
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@ParentPackage("artunion-default")
@Namespace("/userManager")
@Results( {
		@Result(name = "success", type = "dispatcher", location = "/index.jsp"),
		@Result(name = "loginRedirect", type = "redirect", location = "%{#request.cloudContext.params.destPath}"),
		@Result(name = "loginSuccess", type = "redirect", location = "/index.jsp"),
		@Result(name = "login", type = "dispatcher", location = "/index.jsp"),
		@Result(name = "logout", type = "redirect", location = "/index.jsp"),
		@Result(name = "resetPassword", type = "dispatcher", location = "/user/reset_password.jsp"),
		@Result(name = "initUpdateResetPassword", type = "dispatcher", location = "/user/reset_password_update.jsp"),
		@Result(name = "resetPasswordSuccess", type = "dispatcher", location = "/user/reset_password_success.jsp"),
		@Result(name = "carduserRegist", type = "dispatcher", location = "/register/carduser_register.jsp"),
		@Result(name = "register", type = "dispatcher", location = "/register/register.jsp"),
		@Result(name = "center", type = "dispatcher", location = "/userCenter/userInfo.jsp") })
public class UserAction extends BaseAction<UserVO> {
	private static final String LOGINSUCCESS = "loginSuccess";
	private static final String LOGINREDIRECT = "loginRedirect";
	private static final String LOGOUT = "logout";
	private static final String REGISTER = "register";
	private static final String CARDUSERREGIST = "carduserRegist";
	private static final String RESETPASSWORD = "resetPassword";
	private static final String RESETPASSWORD_SUCCESS = "resetPasswordSuccess";
	private static final String INIT_UPDATE_RESET_PASSWORD = "initUpdateResetPassword";
	private static final String CENTER = "center";

	/**
	 * userService
	 */
	@Resource
	private UserService userService;
	/**
	 * 登录的用户
	 */
	private LoginedUser loginInfo;

	private File image; // 上传的文件
	private String imageFileName; // 文件名称
	private String imageContentType; // 文件类型

	/**
	 * base
	 * 
	 * @throws Exception
	 *             exc
	 */
	@Action("/user")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return INPUT;
	}

	/**
	 * 查询
	 * 
	 * @throws Exception
	 *             所有不能处理的异常
	 */
	public String query() throws Exception {
		userService.query(cloudContext);
		return JSON;
	}

	/**
	 * 登录
	 * 
	 * @throws Exception
	 *             所有不能处理的异常
	 */
	public String login() throws Exception {

		cloudContext.addParam(Constant.VERIFY_CODE, getSession().getAttribute(
				Constant.VERIFY_CODE));
		if (StringUtil.isBlank(cloudContext.getVo().getUsername())) {
			cloudContext.addErrorMsg("用户名不能为空");
		}
		if (StringUtil.isBlank(cloudContext.getVo().getPassword())) {
			cloudContext.addErrorMsg("密码不能为空!");
		}
		if (StringUtil.isBlank(cloudContext.getStringParam("checkCode"))) {
			cloudContext.addErrorMsg("验证码不能为空!");
		}
		if (cloudContext.getSuccessIngoreWarn()) {
			userService.login(cloudContext);
			getSession().setAttribute("user", cloudContext.getVo());
			putLoginedUser(cloudContext.getLoginedUser());
		}
		return JSON;
	}

	/**
	 * 登录验证失败后登录
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String validOutLogin() throws SQLException {
		cloudContext.addParam(Constant.VERIFY_CODE, getSession().getAttribute(
				Constant.VERIFY_CODE));
		if (StringUtil.isBlank(cloudContext.getVo().getUsername())) {
			cloudContext.addErrorMsg("用户名不能为空");
		}
		if (StringUtil.isBlank(cloudContext.getVo().getPassword())) {
			cloudContext.addErrorMsg("密码不能为空!");
		}
		if (StringUtil.isBlank(cloudContext.getStringParam("checkCode"))) {
			cloudContext.addErrorMsg("验证码不能为空!");
		}
		if (cloudContext.getSuccessIngoreWarn()) {
			userService.login(cloudContext);
		}
		if (cloudContext.getSuccessIngoreWarn()) {
			if (cloudContext.getBooleanParam("cardUserNoRegist")) {
				return CARDUSERREGIST;
			}
			putLoginedUser(cloudContext.getLoginedUser());
			String url = cloudContext.getStringParam("path").trim();
			if (!StringUtil.isBlank(url) && !url.equals("null")) {
				cloudContext.addParam("destPath", url.replace(getRequest()
						.getContextPath(), ""));
				return LOGINREDIRECT;
			}
			return LOGINSUCCESS;
		} else {
			return LOGIN;
		}
	}

	/**
	 * 注册
	 * 
	 * @throws Exception
	 *             所有不能处理的异常
	 */
	// @Validations(requiredStrings = { @RequiredStringValidator(type =
	// ValidatorType.SIMPLE, fieldName = "userVO.userEntity.username", message =
	// "用户名必须存在") }, regexFields = { @RegexFieldValidator(type =
	// ValidatorType.SIMPLE, fieldName = "userVO.userEntity.username",
	// expression = "[\\w\\-]{4,12}", message = "用户名必须是4-12的字符") })
	public String register() throws Exception {
		if (!cloudContext.getSuccessIngoreWarn()) {// 表单重复提交了
			return SUCCESS;
		}
		userService.addRegister(cloudContext);
		return REGISTER;
	}

	/**
	 * 校内用户完善个人信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String addUserInfo() throws SQLException {
		userService.addUserInfo(cloudContext);
		return CARDUSERREGIST;
	}

	/**
	 * 注销
	 * 
	 * @throws Exception
	 *             所有不能处理的异常
	 */
	public String logout() throws Exception {
		getSession().invalidate();
		return LOGOUT;
	}

	/**
	 * 用户名验证
	 * 
	 * @return
	 * @throws Exception
	 *             exc
	 */
	public String initRegister() throws Exception {
		userService.initRegister(cloudContext);
		return JSON;
	}

	/**
	 * 验证email是否已被使用
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String findByEmail() throws SQLException {
		userService.findByEmail(cloudContext);
		return JSON;
	}

	/**
	 * 重置密码
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String initResetPassword() throws SQLException {
		if (!cloudContext.getSuccessIngoreWarn()) {// 表单重复提交了
			return RESETPASSWORD_SUCCESS;
		}
		cloudContext.addParam(Constant.VERIFY_CODE, getSession().getAttribute(
				Constant.VERIFY_CODE));
		HttpServletRequest request = getRequest();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		cloudContext.addParam("basePath", basePath);
		userService.addResetPassword(cloudContext);
		return cloudContext.getSuccessIngoreWarn() ? RESETPASSWORD_SUCCESS
				: RESETPASSWORD;
	}

	/**
	 * 个人中心修改
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String updateCenter() throws SQLException {
		String realname = null;
		// 得到文件名
		String fileName = imageFileName;

		if (fileName != null) {

			String suffix = fileName.substring(fileName.lastIndexOf("."));// 得到后缀
			realname = UUID.randomUUID().toString() + suffix;
			// 文件保存
			File dir = new File(ProjectUtil.getHeadPic().getAbsoluteFile(), "");
			File saveFile = new File(dir, realname);
			try {
				FileUtils.copyFile(image, saveFile);
			} catch (IOException e) {
			}
		} else {

		}

		cloudContext.getVo().setHeadPic(realname);
		userService.updateCenter(cloudContext);
		userService.center(cloudContext);
		return CENTER;
	}

	/**
	 * 个人中心密码修改
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String updatePassword() throws SQLException {
		userService.updatePassword(cloudContext);
		return JSON;
	}

	public String center() throws SQLException {
		userService.center(cloudContext);
		return CENTER;
	}

	/**
	 * 
	 * @return
	 */
	public String showPic() {
		return NONE;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public String initUpdateResetPassword() throws SQLException {
		userService.initUpdateResetPassword(cloudContext);
		return INIT_UPDATE_RESET_PASSWORD;
	}

	public String resetPassword() throws SQLException {
		userService.updateResetPassword(cloudContext);
		return INIT_UPDATE_RESET_PASSWORD;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public LoginedUser getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginedUser loginInfo) {
		this.loginInfo = loginInfo;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

}
