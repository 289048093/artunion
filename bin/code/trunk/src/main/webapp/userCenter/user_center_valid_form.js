var validSuccess = true;
jq(function() {
	var errorBoxSelecter = '.errorMsg';
	/**
	 * 真实姓名验证
	 */
	jq('#realname').live('blur', function() {
				var realname = jq('#realname').val();
				var $errorMsg = jq('#realname').siblings('.errorMsg');
				var errorMsg = ''
				if (realname == '') {
					errorMsg = '姓名不能为空';
				} else if (realname.length > 20) {
					errorMsg = '长度不能超过20';
				} else if (!/^[a-zA-Z\u4e00-\u9fa5]+$/.test(realname)) {
					errorMsg = '姓名为中文或字母';
				}
				validSuccess = (errorMsg == '' && validSuccess);
				$errorMsg.html(errorMsg);
			});
	
	/**
	 * 邮箱验证
	 */
	jq('#email').live('blur', function() {
				var val = jq('#email').val();
				var $errorMsg = jq('#email').siblings(errorBoxSelecter);
				var errorMsg = '';
				if (val == '') {
					errorMsg = '邮箱不能为空';
				} else if (!/^([\w\.\-])+\@(([\w\-])+\.)+(\w{2,4})+$/.test(val)) {
					errorMsg = '邮箱地址无效！';
				}
				validSuccess = (errorMsg == '' && validSuccess);
				$errorMsg.html(errorMsg);
			});
	/**
	 * 电话验证
	 */
	jq('#telPhone').live('blur', function() {
				var telphone = jq('#telPhone').val();
				var $errorMsg = jq('#telPhone').siblings(errorBoxSelecter);
				var errorMsg = '';
				if (telphone && !/^(0\d{2,3}-?)?\d{5,9}$/.test(telphone)) {
					errorMsg = '*电话号码无效!';
				}
				validSuccess = (errorMsg == '' && validSuccess);
				$errorMsg.html(errorMsg);
			});
	/**
	 * 手机号码验证
	 */
	jq('#mobilePhone').live('blur', function() {
		var cellphone = jq('#mobilePhone').val();
		var $errorMsg = jq('#mobilePhone').siblings(errorBoxSelecter);
		var errorMsg = ''
		if (cellphone == '') {
			errorMsg = '手机号码不能为空！';
		} else if (cellphone
				&& !/^(\+\d{2,4})?1[3\|5\|8]\d{9}$/.test(cellphone)) {
			errorMsg = '手机号码无效！';
		}
		validSuccess = (errorMsg == '' && validSuccess);
		$errorMsg.html(errorMsg);
	});
	jq('#submit').click(function validF() {
				if (!validForm()) {
					return false;
				} else {
					jq('#dform').submit();
				}
			});
	
	});
function valid() {
	validSuccess = true;
	jq('#destPassword').blur();
	jq('#srcPassword').blur();
	jq('#repassword').blur();
	return validSuccess;
}
function validForm() {
	validSuccess = true;
	jq('#realname').blur();
	jq('#mobilePhone').blur();
	jq('#telPhone').blur();
	jq('#email').blur();
	return validSuccess;
}
