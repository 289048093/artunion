var DEFAULT_EACH_PAGE_DATA = 20;

/**
 * 云景全局对象,主要实现sesssion会话失效，和failure同一操作，减少其他js代码量，如果要增加新的参数，按照格式修改。
 */
function CKGobal() {

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
 * 获取JSON集合 的某个JSON对象
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
