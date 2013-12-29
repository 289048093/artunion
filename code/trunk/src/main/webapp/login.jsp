<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.artunion.util.StringUtil"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String srcPath = request.getParameter("path");
    if (StringUtil.isBlank(srcPath)) {
        srcPath = (String) request.getAttribute("path");
    }
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>用户登录</title>
		<STYLE type="text/css">
body {
	margin: 0px;
	background: url("images/index/main_bg1.jpg"); 
}

.ck_login_body {
background: url('images/login_bg.png') no-repeat;
width: 316px;
height: 226px;
}

.error_msg {
	text-align: center;
	height: 15px;
	line-height: 15px;
	color: red;
	font-size: 14px;
}

.title {
	position: relative;
	top: 30px;
	color: #104E8B;
	font-family: "微软雅黑";
	font-size: 16px;
	font-weight: bold;
	text-shadow: 5px 5px 5px #576FCF;
	filter: progid:DXImageTransform.Microsoft.MotionBlur (strength=10,direction = 145 ); 
	*zoom: 1;
}

.form_text {
	border:0px;
	background:url('images/login_text.png') no-repeat;
	width: 160px;
}
.login_button{
background: url('images/login_button.png') no-repeat;
width: 100px;
height: 22px;
border: 0px;
cursor: pointer;
}
.login_table{
margin-top: 40px;
color: white;
font-size: 14px;
font-family: "微软雅黑";
}
</STYLE>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>
	<!--  #F2F2F2; -->
	<body>
		<form action="userManager/user!validOutLogin.action" method="post">
			<input name="artunionContext.params.path" value='<%=srcPath%>'
				type="hidden">
			<div>
				<div align='center'
					style='position: absolute; top: 0px; left: 0px; z-index: 10000; width: 100%; height: 100%;'>
					<s:token></s:token>
					<table align='center' height='100%'>
						<tbody>
							<tr>
								<td>
									<div class="ck_login_body">
										<center>
											<div class="title">
												大学城开放实验室管理平台登录
											</div>
										</center>
										<center>
											<table class="login_table">
												<tr>
													<td>
														<div class="ck_input_label">
															用户名:
														</div>
													</td>
													<td>
														<input type="text" name="artunionContext.vo.username"
															id="username" class="form_text" />
													</td>
												</tr>

												<tr>
													<td>
														<div class="ck_input_label">
															密码:
														</div>
													</td>
													<td>
														<input type="password" name="artunionContext.vo.password"
															id="password" class="form_text">
													</td>
												</tr>
												<tr>
													<td>
														<div class="ck_input_label">
															验证码:
														</div>
													</td>
													<td>
														<input type="text" name="artunionContext.params.checkCode"
															maxlength="4" id="checkCode" class="form_text">
														<img id="verifyCode" src="VerifyCode?Math.random()"
															style="top: 5px; position: relative; cursor: pointer;"
															onclick="(function(o){o.src='VerifyCode?n='+Math.random();document.getElementById('checkCode').focus();})(this)" />
													</td>
												</tr>
												<tr>
													<td colspan="2">
														<center>
															<input type="submit" value="" class="login_button"/>
															<span
																style="font-size: 12px; margin-top: 5px; margin-right: 10px;"><font
																color="gray">|</font>&nbsp;&nbsp;&nbsp;&nbsp;<a
																href="index.jsp">返回首页</a> </span>
														</center>
													</td>
												</tr>
												<tr>
													<td colspan="2">
														<div class="error_msg">
															<s:if
																test="#request.artunionContext.errorMsgList!=null && #request.artunionContext.errorMsgList.size()>0">
																<s:iterator value="#request.artunionContext.errorMsgList"
																	var="msg">
											${msg}
										</s:iterator>
															</s:if>
														</div>
													</td>
												</tr>
											</table>
										</center>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</form>
	</body>
</html>
