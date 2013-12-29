<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@page import="org.apache.struts2.views.jsp.TagUtils"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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

input {
	width: 214px;
	font-size: 13px;
}
.errorMsg{font-size: 13px;}
</style>
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript">
var tipErrorMsg = "";
var tipSuccessMsg = "";
var tipWarnMsg = "";
var rightsUrls = "";<%//提示信息
            List<String> successMsgList = (List<String>) TagUtils.getStack(pageContext).findValue(
                    "#request.cloudContext.successMsgList");
            List<String> warnMsgList = (List<String>) TagUtils.getStack(pageContext).findValue(
                    "#request.cloudContext.warnMsgList");
            List<String> errorMsgList = (List<String>) TagUtils.getStack(pageContext).findValue(
                    "#request.cloudContext.errorMsgList");
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
				alert(tipSuccessMsg+tipWarnMsg);
				location = "<%=basePath%>user/reset_password_success.jsp";
			}else if(tipErrorMsg){
				alert(tipErrorMsg);
			}else{
			}
			var validSuccess = true;
			var errorBoxSelecter = '.errorMsg';
			$(function(){
				/**
				 * 邮箱验证
				 */
				$('#email').live('blur', function() {
					var val = $('#email').val();
					var $errorMsg = $('#email').siblings(errorBoxSelecter);
					var errorMsg = '';
					if (val == '') {
						errorMsg = '邮箱不能为空';
					} else if (!/^([\w\.\-])+\@(([\w\-])+\.)+(\w{2,4})+$/.test(val)) {
						errorMsg = '邮箱地址无效！';
					}
					validSuccess = (errorMsg == '' && validSuccess);
					$errorMsg.html(errorMsg);
				});
			});
				function validForm(){
					validSuccess = true;
					$('input').blur();
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
				<form action="userManager/user!initResetPassword.action" method="post" id="form_">
					<s:token></s:token>
					<table class="register_content_body_tb"
						style="border-collapse: collapse;">
						<tr>
							<td class="register_content_body_tb_name">
								<font class="STYLE1">电子邮箱</font>
							</td>
							<td class="register_content_body_tb_value">
								<input size="30" name="cloudContext.vo.email" id="email" />
								<font style="color: red;">*</font><font style="color: red;"
									class="errorMsg"></font>
							</td>
						</tr>
						<tr>
							<td class="register_content_body_tb_name">
								<font class="STYLE1">验证码</font>
							</td>
							<td class="register_content_body_tb_value">
									<input type="text" name="cloudContext.params.checkCode"
										maxlength="4" id="checkCode" class="form_text"
										style="width: 60px;" onblur="(function(t){if(!t.value){$('#validCodeErrorMsg').html('验证码不能为空');}else{$('#validCodeErrorMsg').html('');}validSuccess = (t.value != '' && validSuccess);})(this)">
									<img id="verifyCode" src="VerifyCode?Math.random()"
										style="top: 5px; position: relative; cursor: pointer;"
										onclick="(function(o){o.src='VerifyCode?n='+Math.random();document.getElementById('checkCode').focus();})(this)" />
									<font style="color: red;">*</font><font style="color: red;"
									class="errorMsg" id="validCodeErrorMsg"></font>
							</td>
						</tr>
						<tr>
							<td height="50" colspan="2" align="center" bgcolor="#FFFFFF">
								<input name="button" type="submit" class="b_tn" value=" 确 定 "
									onclick="return validForm()" />
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input name="button2" type="reset" class="b_tn" value=" 重 置 " />
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>
