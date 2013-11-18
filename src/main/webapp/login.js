// 插件依赖包导入
Ext.Loader.setConfig({
			enabled : true
		});
Ext.Loader.setPath('Ext.ux', 'extjs/ux');
Ext.require(['Ext.ux.form.VerifyCodeField']);

Ext.onReady(function() {
	// form
	var formPanel = Ext.create('Ext.form.Panel', {
				id : "loginForm",
				layout : 'anchor',
				border : false,
				standardSubmit : true,
				defaults : {
					anchor : '100%'
				},
				items : [{
							xtype : 'component',
							autoEl : {
								tag : 'img',
								src : 'images/loginTitle.png'
							}
						}, {
							xtype : "tabpanel",
							height : 145,
							id : "tabpanel",
							defaults : {
								bodyStyle : 'padding:10px'
							},
							border : false,
							items : [{
								title : "用户登录",
								id : "loginTab",
								items : [{
									xtype : "textfield",
									fieldLabel : '用户名',
									labelWidth : 50,
									name : 'cloudContext.vo.username',
									id : "usernameId",
									listeners : {
										specialkey : function(field, e) {
											if (e.getKey() == Ext.EventObject.ENTER) {
												login();
											}
										}
									},
									validator : function(value) {
										var tabId = Ext.getCmp("tabpanel")
												.getActiveTab().getId();
										if (tabId == "loginTab") {
											if (value.length < 4
													|| value.length > 12) {
												return "用户名的长度必须大于等于4，小于等于12";
											} else {
												return true;
											}
										} else {
											return true;
										}
									}
								}, {
									xtype : "textfield",
									fieldLabel : '密&nbsp;&nbsp;&nbsp;码',
									labelWidth : 50,
									name : 'cloudContext.vo.password',
									id : 'passwordId',
									inputType : "password",
									listeners : {
										specialkey : function(field, e) {
											if (e.getKey() == Ext.EventObject.ENTER) {
												login();
											}
										}
									},
									validator : function(value) {
										var tabId = Ext.getCmp("tabpanel")
												.getActiveTab().getId();
										if (tabId == "loginTab") {
											if (value.length < 4
													|| value.length > 12) {
												return "密码的长度必须大于等于4，小于等于12";
											} else {
												return true;
											}
										} else {
											return true;
										}
									}
								}, {
									xtype : "verifyfield",
									fieldLabel : '验证码',
									labelWidth : 50,
									name : 'cloudContext.params.verifyCode',
									id : 'verifyCodeId',
									blankText : '验证码不能为空',
									width : 160,
									listeners : {
										specialkey : function(field, e) {
											if (e.getKey() == Ext.EventObject.ENTER) {
												login();
											}
										}
									},
									validator : function(value) {
										var tabId = Ext.getCmp("tabpanel")
												.getActiveTab().getId();
										if (tabId == "loginTab") {
											if (value.length != 4) {
												return "验证码的长度必须等于4";
											} else {
												return true;
											}
										} else {
											return true;
										}
									}
								}]
							}]

						}]
			});

	// window
	var win = new Ext.Window({
				title : "艺考部落",
				layout : 'fit',
				width : 460,
				height : 300,
				modal : true,
				draggable : false,
				closable : false,
				resizable : false,
				border : false,
				items : formPanel,
				buttons : [{
					text : '登录',
					id : "submitBtn",
					formBind : true,
					handler : function() {
						var tabId = Ext.getCmp("tabpanel").getActiveTab()
								.getId();
						if (tabId == "loginTab") {
							login();
						} else if (tabId = "registerTab") {
							register();
						}
					}
				}, {
					text : '重置',
					handler : function() {
						Ext.getCmp("loginForm").getForm().reset();
					}
				}]
			});

	// 设置焦点
	win.on('show', function() {
				var username = Ext.getCmp('usernameId');
				username.focus(true);
			});
	win.show();

	// 判断是否有错误信息，若有则打印
	var errorStr = jq("#errorFieldId").val();
	if (errorStr.length > 0 && /\!/g.test(location)) {
		Ext.Msg.alert("错误提示", errorStr, function() {
				}).focus(true);
	}
});

// 登录
function login() {

	var form = Ext.getCmp("loginForm").getForm();
	form.url = "userManager/base!login.action";
	if (form.isValid()) {
		form.submit();
	}

}
// 注册
function register() {
	var form = Ext.getCmp("loginForm").getForm();
	form.url = "userManager/base!register.action";
	if (form.isValid()) {
		form.submit();
	}
}