var DEFAULT_EACH_PAGE_DATA = 20;

/**
 * 云景全局对象,主要实现sesssion会话失效，和failure同一操作，减少其他js代码量，如果要增加新的参数，按照格式修改。
 */
function CKGobal() {

}
CKGobal.ajax = function(param) {
	var url = param.url;
	var method = Ext.isEmpty(param.method) ? "POST" : param.method;
	var async = Ext.isEmpty(param.async) ? true : param.async;
	var params = param.params;
	var success = Ext.isEmpty(param.success) ? null : param.success;
	var failure = Ext.isEmpty(param.failure) ? null : param.failure;
	var autoErrorTip = Ext.isEmpty(param.autoErrorTip)
			? true
			: param.autoErrorTip;
	var autoMsgTip = Ext.isEmpty(param.autoMsgTip) ? true : param.autoMsgTip;
	var enableArray = Ext.isEmpty(param.enableArray) ? null : param.enableArray;
	Ext.Ajax.request({
				url : url,
				method : method,
				async : async,
				params : params,
				success : function(response, action) {
					resultJSON = Ext.JSON.decode(response.responseText);
					if (resultJSON.sessionError != null) {
						Ext.Msg.alert('提示', '会话超时，请重新登录!', function() {
									parent.location.href = 'index.jsp';
								});
						enableObjs(enableArray);
						return;
					}
					if (resultJSON.rightsError != null) {
						Ext.Msg.alert('提示', '你没有此权限!');
						enableObjs(enableArray);
						return;
					}
					if (resultJSON.systemError != null) {
						Ext.Msg.alert('提示', '系统错误!');
						enableObjs(enableArray);
						return;
					}
					if (!resultJSON.artunionContext.success) {
						if (autoErrorTip) {
							errorList = resultJSON.artunionContext.errorMsgList;
							var errorStr = "";
							for (var i = 0; i < errorList.length; i++) {
								errorStr += errorList[i] + "</br>";
							}
							Ext.Msg.alert('错误提示', errorStr, function() {
									});
							enableObjs(enableArray);
							return;
						}
					}
					// 可以根据这个Flag做判断是否alert等操作。
					var havaMsgFlag = false;
					if (autoMsgTip) {
						var msgList = resultJSON.artunionContext.errorMsgList;
						if (msgList.length > 0) {
							havaMsgFlag = true;
							var msgStr = "";
							for (var i = 0; i < msgList.length; i++) {
								msgStr += msgList[i] + "</br>";
							}
							Ext.Msg.alert('信息提示', msgStr, function() {
									});
						}
					}
					if (success == null) {
						alert("没有定义success方法");
					} else {
						success(response, action, resultJSON,havaMsgFlag);
					}
					enableObjs(enableArray);
				},
				failure : function(response, options) {
					if (failure == null) {
						alert("会话超时，或系统错误");
					} else {
						failure(response, options);
					}
				}
			});
}
/**
 * AJAX返回后，开启组件enable
 * 
 * @param {}
 *            enableArray
 */
function enableObjs(enableArray) {
	if (enableArray != null) {
		for (var i = 0; i < enableArray.length; i++) {
			enableArray[i].setDisabled(false);
		}
	}
}
/**
 * 获取param
 * 
 * @param {}
 *            param
 * @return {}
 */
CKGobal.getUrlParam = function(param) {
	var url = window.location.href;
	var re = new RegExp("(///?|&)" + param + "=([^&]+)(&|$)", "i");
	var m = url.match(re);
	if (m) {
		return m[2];
	} else {
		return '';
	}
}

/**
 * json转换成string
 * 
 * @param {}
 *            obj
 * @return {}
 */
CKGobal.jsonToString = function(obj) {
	var THIS = this;
	switch (typeof(obj)) {
		case 'string' :
			return '"' + obj.replace(/(["\\])/g, '\\$1') + '"';
		case 'array' :
			return '[' + obj.map(THIS.jsonToString).join(',') + ']';
		case 'object' :
			if (obj instanceof Array) {
				var strArr = [];
				var len = obj.length;
				for (var i = 0; i < len; i++) {
					strArr.push(THIS.jsonToString(obj[i]));
				}
				return '[' + strArr.join(',') + ']';
			} else if (obj == null) {
				return 'null';

			} else {
				var string = [];
				for (var property in obj)
					string.push(THIS.jsonToString(property) + ':'
							+ THIS.jsonToString(obj[property]));
				return '{' + string.join(',') + '}';
			}
		case 'number' :
			return obj;
		case false :
			return obj;
	}
}

/**
 * 获取JSON 的某个JSON对象
 * 
 * @param {}
 *            param
 * @return {}
 */
CKGobal.findJSON = function(jsonCollection, findStr) {
	var jsonCollStr = CKGobal.jsonToString(jsonCollection);
	var re = new RegExp("{[^{]*" + findStr + "[^}]*}", "i");
	var m = jsonCollStr.match(re);
	if (m) {
		return eval("(" + m[0] + ")");;
	} else {
		return {};
	}
}
