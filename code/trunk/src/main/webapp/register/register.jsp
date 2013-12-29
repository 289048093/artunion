<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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

		<title>用户注册</title>
		<link rel="stylesheet" type="text/css"
			href="<%=basePath%>css/register.css">
		<style type="text/css">
		.register_content_body{font-size: 13px;}
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
		<script type="text/javascript" src="register/register.js"></script>
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
</script>
	</head>
	<body>
		<div class="register_content">
			<div class="register_content_title">
				<img style="margin-left: 10px;" src="images/dh.gif"></img>
				<span>首页 ->用户注册</span>
			</div>
			<div class="register_content_body">
				<form action="userManager/user!register.action" method="post" onsubmit="return validForm()">
					<s:token></s:token>
					<table class="register_content_body_tb"
						style="border-collapse: collapse;">
						<tr>
							<td class="register_content_body_tb_name">
								<font class="STYLE1">用户名</font>
							</td>
							<td class="register_content_body_tb_value">
								<input id="username" size="30" name="artunionContext.vo.username" />
								<font style="color: red;">*</font><font style="color: red;"
									class="errorMsg"></font>
							</td>
						</tr>
						<tr>
							<td class="register_content_body_tb_name">
								<font class="STYLE1">密码</font>
							</td>
							<td height="25" align="left" bgcolor="#FFFFFF">
								<input type="password" size="30" name="artunionContext.vo.password"
									id="password" maxlength="30" />
								<font style="color: red;">*</font><font style="color: red;"
									class="errorMsg"></font>
							</td>
						</tr>
						<tr>
							<td class="register_content_body_tb_name">
								<font class="STYLE1">确认密码</font>
							</td>
							<td height="25" align="left" bgcolor="#FFFFFF">
								<input type="password" size="30" name="" id="repassword" />
								<font style="color: red;">*</font><font style="color: red;"
									class="errorMsg"></font>
							</td>
						</tr>
						<tr>
							<td class="register_content_body_tb_name">
								<font class="STYLE1">真实姓名</font>
							</td>
							<td class="register_content_body_tb_value">
								<input size="30" name="artunionContext.vo.realname" id="realname" />
								<font style="color: red;">*</font><font style="color: red;"
									class="errorMsg"></font>
							</td>
						</tr>
						<tr>
							<td class="register_content_body_tb_name">
								<font class="STYLE1">性别</font>
							</td>
							<td>
								<input type="radio" name="artunionContext.vo.sex" value="1"
									style="width: 14px;" checked="checked" />
								男
								<input type="radio" name="artunionContext.vo.sex" value="2"
									style="width: 14px;" />
								女
								<!--<select name="artunionContext.vo.sex">
									<option value="1">
										男
									</option>
									<option value="2">
										女
									</option>
								</select>
							-->
							</td>
						</tr>
						<tr>
							<td class="register_content_body_tb_name">
								<font class="STYLE1">单位名称</font>
							</td>
							<td class="register_content_body_tb_value">
								<input size="30" name="artunionContext.vo.company.name"
									id="companyName" />
							</td>
						</tr>

						<tr>
							<td class="register_content_body_tb_name">
								<font class="STYLE1">手机</font>
							</td>
							<td class="register_content_body_tb_value">
								<input size="30" name="artunionContext.vo.mobilePhone"
									id="mobilePhone" />
								<font style="color: red;">*</font><font style="color: red;"
									class="errorMsg"></font>
							</td>
						</tr>
						<tr>
							<td class="register_content_body_tb_name">
								<font class="STYLE1">电话</font>
							</td>
							<td class="register_content_body_tb_value">
								<input size="30" name="artunionContext.vo.telPhone" id="telPhone" />
								<font class="errorMsg"></font>
							</td>
						</tr>
						<tr>
							<td class="register_content_body_tb_name">
								<font class="STYLE1">电子邮箱</font>
							</td>
							<td class="register_content_body_tb_value">
								<input size="30" name="artunionContext.vo.email" id="email" />
								<font style="color: red;">*</font><font style="color: red;"
									class="errorMsg"></font>
							</td>
						</tr>
						<tr>
							<td class="register_content_body_tb_name">
								<font class="STYLE1">地址</font>
							</td>
							<td class="register_content_body_tb_value">
								<textarea rows="3" cols="27" name="artunionContext.vo.addr"
									id="addr"></textarea>
								<font style="color: red;">*</font><font style="color: red;"
									class="errorMsg"></font>
							</td>
						</tr>
						<tr>
							<td height="50" colspan="2" align="center" bgcolor="#FFFFFF">
								<input name="button" type="submit" id="submitBtn" class="b_tn"
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
