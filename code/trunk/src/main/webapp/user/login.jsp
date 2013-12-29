<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户登录</title>
	</head>
	<body>
		<form action="userManager/user!login.action" method="post">
			<table>
				<tr>
					<td>
						用户名:
					</td>
					<td>
						<input type="text" name="cloudContext.vo.name">
					</td>
				</tr>
				<tr>
					<td>
						密码:
					</td>
					<td>
						<input type="text" name="cloudContext.vo.password">
					</td>
				</tr>
				<tr>
					<td>
						验证码:
					</td>
					<td>
						<input type="text" name="cloudContext.params.name" />
						<img src="VerifyCode?Math.random()">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit">
						<input type="reset">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>