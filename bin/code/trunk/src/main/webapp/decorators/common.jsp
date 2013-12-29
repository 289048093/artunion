<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<decorator:usePage id="decoratedPage" />
<html>
	<head>
		<base href="<%=basePath%>">

		<title><decorator:title default="艺考部落" /></title>
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
		<link rel="stylesheet" href="css/main.css" type="text/css"></link>
		<decorator:head />
	</head>
	<body>
		<div class="main">
			<!-- 头部：logo，导航 -->
			<div id="top" align="center">
				<img src="#" border="0"></img>
				<div class="dh_">
					<a href="index.jsp">首页</a>&nbsp;|&nbsp;
				</div>
			</div>
			<!-- 中部：列表，内容 -->
			<div class="content">
				<decorator:body />
				<div class="b_q">
					版权所有 &copy; 2013 艺考部落  ICP备 号
				</div>
			</div>
		</div>
	</body>
</html>
