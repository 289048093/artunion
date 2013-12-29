<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

		<title>用户资料</title>


		<script type="text/javascript">
Ext.onReady(function() {
	Ext.QuickTips.init();
});
</script>

		<script type="text/javascript" src="userCenter/updatePass.js">
</script>

		<script type="text/javascript"
			src="userCenter/user_center_valid_form.js">
</script>
		<style type="text/css">
.userInfoTable tbody tr th {
	text-align: right;
	font-weight: normal;
	padding: 14px 10px 0 0;
	width: 90px;
}

.userInfoTable tbody tr td {
	text-align: left;
	font-weight: normal;
	padding: 14px 10px 0 0;
}

table {
	font-size: 13px;
	margin-left: 100px;
	margin-top: 10px;
}

.errorMsg {
	color: red;
}

.editInfoBtn {
	font-size: 12px;
	background: transparent url(images/edit.png) no-repeat 0 1px;
	line-height: 20px;
	text-decoration: none;
	color: #09498B;
	text-align: right;
	display: inline-block;
	width: 70px;
	_zoom: 1;
	_display: inline;
}
</style>

	</head>

	<body>



		<div>
			<form action="userManager/user!updateCenter.action" id="dform"
				method="post" enctype="multipart/form-data">
				<input type="hidden"
					value='<s:property value="#request.cloudContext.vo.id"/>'
					id="userId" name="cloudContext.vo.id" />
				<table class="userInfoTable">
					<tr>
						<td>
							用户名
						</td>
						<td>
							<span>${cloudContext.vo.username}</span> &nbsp;&nbsp;&nbsp;&nbsp;
							<div class="editInfoBtn">
								<a href="javascript:void(0)"
									onclick="updatePass(${cloudContext.vo.id})">密码修改</a>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<img src="${cloudContext.vo.headPic}"
								style="width: 50px; height: 50px;">
						</td>
						<td>
							<input type="file" name="image">
						</td>
					</tr>
					<tr>
						<td>
							性别
						</td>
						<td>
							<s:if test="#request.cloudContext.vo.sex==1">
								<input type="radio" name="cloudContext.vo.sex" value="1"
									checked="checked" />男
  <input type="radio" name="cloudContext.vo.sex" value="2" />女
  </s:if>
							<s:else>
								<input type="radio" name="cloudContext.vo.sex" value="1" />男
  <input type="radio" name="cloudContext.vo.sex" value="2"
									checked="checked" />女
  </s:else>
						</td>
					</tr>
					<tr>
						<td>
							email
						</td>
						<td>
							<input type="text" name="cloudContext.vo.email"
								value="${cloudContext.vo.email}" id="email">
							<font color="red">*</font><font class="errorMsg"></font>
						</td>
					</tr>
					<tr>
						<td>
							手机
						</td>
						<td>
							<input type="text" name="cloudContext.vo.mobilePhone"
								value="${cloudContext.vo.mobilePhone}" id="mobilePhone">
							<font color="red">*</font><font class="errorMsg"></font>
						</td>
					</tr>
					<tr>
						<td>
							电话
						</td>
						<td>
							<input type="text" name="cloudContext.vo.telPhone"
								value="${cloudContext.vo.telPhone}" id="telPhone" />
							<font class="errorMsg"></font>
						</td>
					</tr>
					<tr>
						<td>
							真实姓名
						</td>
						<td>
							<input type="text" name="cloudContext.vo.realname"
								value="${cloudContext.vo.realname}" id="realname" />
							<font color="red">*</font><font class="errorMsg"></font>
						</td>
					</tr>
					<tr>
						<td>
							单位
						</td>
						<td>
							<input type="text" name="cloudContext.vo.company"
								value="${cloudContext.vo.company}" />
							<font class="errorMsg"></font>
						</td>
					</tr>
					<tr>
						<td>
							地址
						</td>
						<td>
							<input type="text" name="cloudContext.vo.addr"
								value="${cloudContext.vo.addr}" />
							<font class="errorMsg"></font>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<center>
								<input type="submit" value="提交" id="submit" class="x-btn" />
							</center>
						</td>
					</tr>

				</table>

			</form>

		</div>




	</body>
</html>
