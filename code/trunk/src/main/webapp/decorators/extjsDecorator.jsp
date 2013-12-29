<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<decorator:usePage id="decoratedPage" />
<html>

	<head>
		<link rel="shortcut icon" href="images/favicon.ico"
			type="image/x-icon" />
		<title><decorator:title default="艺考部落" /></title>

		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css"
			href="extjs/resources/css/ext-all.css">
		<link rel="stylesheet" type="text/css"
			href="extjs/resources/css/ext-icon.css">
		<link rel="stylesheet" type="text/css" href="css/extjs_main.css">
		<style type="text/css">
#loading-mask {
	Z-INDEX: 20000;
	LEFT: 0px;
	WIDTH: 100%;
	POSITION: absolute;
	TOP: 0px;
	HEIGHT: 100%;
	BACKGROUND-COLOR: white
}

#loading {
	PADDING-RIGHT: 2px;
	PADDING-LEFT: 2px;
	Z-INDEX: 20001;
	LEFT: 45%;
	PADDING-BOTTOM: 2px;
	PADDING-TOP: 2px;
	POSITION: absolute;
	TOP: 40%;
	HEIGHT: auto
}

#loading IMG {
	MARGIN-BOTTOM: 5px
}

#loading .loading-indicator {
	PADDING-RIGHT: 10px;
	PADDING-LEFT: 10px;
	BACKGROUND: white;
	PADDING-BOTTOM: 10px;
	MARGIN: 0px;
	FONT: 12px 宋体, arial, helvetica;
	COLOR: #555;
	PADDING-TOP: 10px;
	HEIGHT: auto;
	TEXT-ALIGN: center
}
</style>
		<script type="text/javascript" src="extjs/ext-all.js"></script>
		<script type="text/javascript" src="extjs/ext-lang-zh_CN.js"></script>
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="js/gobal.js"></script>
		<script type="text/javascript" src="js/projectutil.js"></script>
		<script type="text/javascript" src="extjs/datetime/TimePickerField.js"></script>
		<script type="text/javascript" src="extjs/datetime/DateTimePicker.js"></script>
		<script type="text/javascript" src="extjs/datetime/DateTimeField.js"></script>
		<script type="text/javascript" src="extjs/datetime/DateTimeMenu.js"></script>
		<script type="text/javascript">
<!--
	Ext.QuickTips.init();
	Ext.Loader.setConfig({
				enabled : true
			});
	//jquery让出$符号
	var jq = jQuery.noConflict();
			

	jq(function(){
		Ext.MessageBox.msgButtons[0].setText("确定");
		Ext.MessageBox.msgButtons[1].setText("是");
		Ext.MessageBox.msgButtons[2].setText("否");
		Ext.MessageBox.msgButtons[3].setText("取消");
	
	});

//-->
</script>
		<decorator:head />
	</head>
	<body>
		<%
		    //如果<meta name="mask" content="true">的值mask 没有或者为true时，则表明需要遮罩层
		    boolean mask = decoratedPage.getProperty("meta.mask") == null ? true : Boolean.valueOf(decoratedPage
		            .getProperty("meta.mask"));
		    if (mask) {
		%>
		<script type="text/javascript">
					//初始界面遮罩层
			Ext.EventManager.on(window, 'load', function(){
			 setTimeout(
				 function() {
					Ext.get('loading').remove();
					Ext.get('loading-mask').fadeOut({remove:true});
					}, 200); 
		
			});
		</script>
		<DIV id="loading-mask"></DIV>
		<div id="loading">
			<DIV class="loading-indicator">
				<IMG style="MARGIN-RIGHT: 8px" height="32" src="images/loading.gif"
					width="36" align="absMiddle">
				正在初始化,请稍等...
			</DIV>
		</div>
		<%
		    }
		%>
		<input type="hidden" value="<%=request.getServletPath()%>"
			id="requesturlId" />
		<decorator:body />
	</body>
</html>
