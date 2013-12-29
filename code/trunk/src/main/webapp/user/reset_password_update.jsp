<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.apache.struts2.views.jsp.TagUtils"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>找回密码</title>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>css/register.css">
		<style type="text/css">
.STYLE1 {
	width: 80px;
	text-align: right;
	font-size: 13px;
}
.errorMsg{font-size: 13px;}
input {
	width: 214px;
}
</style>
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript">
var tipErrorMsg = "";
var tipSuccessMsg = "";
var tipWarnMsg = "";
var rightsUrls = "";<%//提示信息
            List<String> successMsgList = (List<String>) TagUtils.getStack(pageContext).findValue(
                    "#request.artunionContext.successMsgList");
            List<String> warnMsgList = (List<String>) TagUtils.getStack(pageContext).findValue(
                    "#request.artunionContext.warnMsgList");
            List<String> errorMsgList = (List<String>) TagUtils.getStack(pageContext).findValue(
                    "#request.artunionContext.errorMsgList");
            if (successMsgList != null && successMsgList.size() > 0) {
                for (int i = 0; i < successMsgList.size(); i++) {
                    out.println(String.format("tipSuccessMsg+=\"%1$s\"\n", successMsgList.get(i).replaceAll("\"", "'")));
                }
            }
            if (warnMsgList != null && warnMsgList.size() > 0) {
                for (int i = 0; i < warnMsgList.size(); i++) {
                    out.println(String.format("tipWarnMsg+=\"%1$s\"\n", warnMsgList.get(i).replaceAll("\"", "'")));
                }
            }
            if (errorMsgList != null && errorMsgList.size() > 0) {
                for (int i = 0; i < errorMsgList.size(); i++) {
                    out.println(String.format("tipErrorMsg+=\"%1$s\"\n", errorMsgList.get(i).replaceAll("\"", "'")));
                }
            }%>
			if(tipSuccessMsg || tipWarnMsg){
				alert(tipSuccessMsg +","+ tipWarnMsg);
				location = "<%=basePath%>index.jsp";
			}else if(tipErrorMsg){
				alert(tipErrorMsg);
				location = "<%=basePath%>index.jsp";
			}else{
			}
			var validSuccess = true;
			var errorBoxSelecter = '.errorMsg';
			$(function(){
					/**
					 * 密码验证
					 */
					$('#password').live('blur', function() {
						var pwd = $('#password').val();
						var $errorMsg = $('#password')
								.siblings(errorBoxSelecter);
						var errorMsg = '';
						if (pwd == '') {
							errorMsg = '密码不能为空';
						} else if (pwd.length < 6) {
							errorMsg = '密码长度不能少于六位';
						}
						validSuccess = (errorMsg == '' && validSuccess);
						$errorMsg.html(errorMsg);
					});
					/**
					 * 确认密码
					 */
					$('#repassword').live('blur', function() {
								var $errorMsg = $('#repassword')
										.siblings(errorBoxSelecter);
								var rePwd = $('#repassword').val();
								var pwd = $('#password').val();
								var errorMsg = '';
								if (rePwd != pwd) {
									errorMsg = '两次密码输入不一致';
								}
								validSuccess = (errorMsg == '' && validSuccess);
								$errorMsg.html(errorMsg);
							});
			});
				function validForm(){
					validSuccess = true;
					$('input').blur();
					if(validSuccess){
						$('#submitBtn').attr('disabled',true);
					}
					return validSuccess;
				}
</script>
	</head>
	<body>
		<div class="register_content">
			<div class="register_content_title">
				<img style="margin-left: 10px;" src="images/dh.gif"></img>
				<span>首页 ->找回密码</span>
			</div>
			<div class="register_content_body">
				<form action="userManager/user!resetPassword.action" method="post"
					id="form_" onsubmit="return validForm()">
					<s:token></s:token>
					<input type="hidden" name="artunionContext.params.key"
						value='<s:property value="#request.artunionContext.params.key"/>'>
					<table class="register_content_body_tb"
						style="border-collapse: collapse;">
						<tr>
							<td class="register_content_body_tb_name">
								<font class="STYLE1">用户名</font>
							</td>
							<td class="register_content_body_tb_value">
								<div>
									<s:property value="#request.artunionContext.vo.username" />
								</div>
							</td>
						</tr>
						<tr>
							<td class="register_content_body_tb_name">
								<font class="STYLE1">新密码</font>
							</td>
							<td class="register_content_body_tb_value">
								<input type="password" size="30" name="artunionContext.vo.password" id="password" />
								<font style="color: red;">*</font><font style="color: red;"
									class="errorMsg"></font>
							</td>
						</tr>
						<tr>
							<td class="register_content_body_tb_name">
								<font class="STYLE1">确认密码</font>
							</td>
							<td class="register_content_body_tb_value">
								<input type="password" size="30" name="artunionContext.vo.repassword"
									id="repassword" />
								<font style="color: red;">*</font><font style="color: red;"
									class="errorMsg"></font>
							</td>
						</tr>
						<tr>
							<td height="50" colspan="2" align="center" bgcolor="#FFFFFF">
								<input name="button" id="submitBtn" type="submit" class="b_tn"
									value=" 确 定 " />
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input name="button2" type="reset" class="b_tn" value=" 重 置 " />
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>
