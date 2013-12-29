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
}

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
				alert(tipSuccessMsg+tipWarnMsg);
				location = "<%=basePath%>index.jsp";
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
				<span>当前位置：首页 >> 找回密码</span>
			</div>
			<div class="register_content_body">
				您的密码找回成功，重置密码链接已发送到您的邮箱<s:property value="#request.artunionContext.vo.email"/>，请注意查收，并及时设置新密码，邮件24小时内有效
			</div>
		</div>
	</body>
</html>
