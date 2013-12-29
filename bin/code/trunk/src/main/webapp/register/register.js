var validSuccess = true;
$(function() {

			var errorBoxSelecter = '.errorMsg';

			$('#username').on('blur', function() {
				var username = $('#username').val();
				var $errorMsg = $('#username').siblings(errorBoxSelecter);
				var errorMsg = ''
				if (username == '') {
					errorMsg = '用户名不能为空';
				} else if (username.length < 4 || username.length > 20) {
					errorMsg = '长度不能少于4或超过20';
				} else if (!/^[a-zA-Z0-9_]*$/.test(username)) {
					errorMsg = '用户名由大写字母、小写字母、下划线组成';
				}
				validSuccess = (errorMsg == '' && validSuccess);
				$errorMsg.html(errorMsg);
				if (username != null && username != '') {
					$.get(
							"userManager/user!initRegister.action?cloudContext.vo.username="
									+ username, callback);
				}
				function callback(data) {
					var exist = data.cloudContext.params.usernameExist;
					var errorMsg = '';
					if (exist) {
						errorMsg = "用户名已存在。"
						validSuccess = (errorMsg == '' && validSuccess);
						$errorMsg.html(errorMsg);
					}
				}
			});
			$('#realname').live('blur', function() {
						var realname = $('#realname').val();
						var $errorMsg = $('#realname')
								.siblings(errorBoxSelecter);
						var errorMsg = ''
						if (realname == '') {
							errorMsg = '真实姓名不能为空';
						} else if (realname.length > 20) {
							errorMsg = '长度不能超过20';
						} else if (!/^[a-zA-Z\u4e00-\u9fa5]+$/.test(realname)) {
							errorMsg = '真实姓名为中文或字母';
						}
						validSuccess = (errorMsg == '' && validSuccess);
						$errorMsg.html(errorMsg);
					});
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
				if (val) {
					$.get(
							"userManager/user!findByEmail.action?cloudContext.vo.email="
									+ val, callback);
				}
				function callback(data) {
					var exist = data.cloudContext.params.emailExist;
					var errorMsg = '';
					if (exist) {
						errorMsg = "该电子邮箱已经被注册。"
						validSuccess = (errorMsg == '' && validSuccess);
						$errorMsg.html(errorMsg);
					}
				}
			});
			/**
			 * 电话验证
			 */
			$('#telphone').live('blur', function() {
						var telphone = $('#telphone').val();
						var $errorMsg = $('#telphone')
								.siblings(errorBoxSelecter);
						var errorMsg = '';
						if (telphone
								&& !/^(0\d{2,3}-?)?\d{5,9}$/.test(telphone)) {
							errorMsg = '电话号码无效!';
						}
						validSuccess = (errorMsg == '' && validSuccess);
						$errorMsg.html(errorMsg);
					});
			/**
			 * 手机号码验证
			 */
			$('#mobilePhone').live('blur', function() {
				var mobilePhone = $('#mobilePhone').val();
				var $errorMsg = $('#mobilePhone').siblings(errorBoxSelecter);
				var errorMsg = ''
				if (mobilePhone == '') {
					errorMsg = '手机号码不能为空！';
				} else if (mobilePhone
						&& !/^(\+\d{2,4})?1[3\|5\|8]\d{9}$/.test(mobilePhone)) {
					errorMsg = '手机号码无效！';
				}
				validSuccess = (errorMsg == '' && validSuccess);
				$errorMsg.html(errorMsg);
			});
			/**
			 * 地址验证
			 */
			$('#addr').live('blur', function() {
						var addr = $(this).val();
						var $errorMsg = $(this).siblings(errorBoxSelecter);
						var errorMsg = '';
						if (addr.length > 255) {
							errorMsg = '地址长度必须小于255';
						}
						validSuccess = (errorMsg == '' && validSuccess);
						$errorMsg.html(errorMsg);
					});
		})
function validForm() {
	validSuccess = true;
	$('#username').blur();
	$('#password').blur();
	$('#repassword').blur();
	$('#telPhone').blur();
	$('#mobilePhone').blur();
	$('#email').blur();
	$('#addr').blur();
	$('#realname').blur();
	if (validSuccess) {
		$('#submitBtn').attr('disabled', true);
	}
	return validSuccess;
}