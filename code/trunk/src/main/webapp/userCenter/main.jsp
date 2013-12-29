<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
	<head>
		<title>深圳大学城开放实验室服务平台个人中心</title>
		

			<s:if test="#session.userLogin==null">
			<script type="text/javascript">
				alert("会话超时或没登录！");
				location.href = 'index.jsp';
			</script>
		</s:if>
			<script type="text/javascript" src="userCenter/main.js"></script>
			<script type="text/javascript">
				var menuTree = [{
									text : "个人资料管理",
									contentTitle : "个人资料管理",
									leaf : true,
									url : "userManager/user!center.action",
									tabId:"0"
								}, {
									text : "我申请的仪器设备",
									contentTitle : "我申请的仪器设备",
									leaf : true,
									url : "userCenter/ApplyOrder_Equipment.jsp",
									tabId:"1"
								}, {
									text : "我申请的行业检测",
									contentTitle : "我申请的行业检测",
									leaf : true,
									url : "userCenter/ApplyOrder_IndustryTest.jsp",
									tabId:"2"
								}, {
									text : "我的问答",
									contentTitle : "我的问答",
									leaf : true,
									url : "userCenter/myqaa.jsp",
									tabId:"3"
								}];
				var treeNodes =  {
								root : {
									tabId : "treeid_0",
									text : 'Root',
									children : [{
												text : "个人中心",
												expanded : true,
												noClick:true,
						
												children : menuTree
											}]
								}
							}
				var linkflag = <%=request.getParameter("linkFlag")%>
			</script>
	
	</head>
	<body>
		<input type="hidden" value="${userLogin.realname }"
			id="session_realnameId" />
		<input type="hidden" value="${userLogin.id }"
			id="session_userNameId" />
			<input type="hidden" value="${userLogin.lastLoginTime }"
			id="session_lastLoginTimeId" />
		<div id="north">
			<table border="0" cellpadding="0" cellspacing="0" width="100%"
				height="60" background="images/main_title.png">
				<tr>
					<td style="padding-left: 15px" valign="top">
						<div class="banner_title">深圳大学城开放实验室服务平台个人中心</div>
					</td>
					<td style="padding-right: 5px">
						<table width="100%" height="100%" border="0" cellpadding="0"
							cellspacing="3"
							style="font-family: '宋体'; font-size: 12px; color: 4798D7;">
							<tr align="right">
								<td>
									<div id="realTime"></div>
								</td>
							</tr>
							<tr align="right">
								<td>
									<table border="0" cellpadding="0" cellspacing="1">
										<tr>
											<td valign="top">
												<div id="themeDiv">
												</div>
											</td>
											<td>
												&nbsp;
											</td>
											<td valign="top">
												<div id="configDiv">
												</div>
											</td>
											<td>
												&nbsp;
											</td>
											<td>
												<div id="screenDiv">
												</div>
											</td>
											<td>
												&nbsp;
											</td>
											<td valign="top">
												<div id="indexButton">
												</div>
											</td>
											<td>
												&nbsp;
											</td>
											<td valign="top">
												<div id="closeDiv">
												</div>
											</td>
											
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>


