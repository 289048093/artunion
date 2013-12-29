<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.artunion.util.StringUtil"%>
<%@page import="com.artunion.util.Constant"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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
		<title>首页</title>
		<link rel="stylesheet" type="text/css"
			href="css/index.css">
		<script type="text/javascript" src="js/index.js"></script>
		<script type="text/javascript" src="slider.js"></script>
	</head>
	<body>
	 首页信息内容。。。。。。
	</body>
</html>
