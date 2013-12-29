
Ext.require(['Ext.grid.*', 'Ext.data.*', 'Ext.dd.*']);
var updatePassWindow;
var updatePassPanel;
var id;
function updatePass(id){
	id=id;
	updatePassPanel = new Ext.form.FormPanel( {
		id : 'updatePassPanelId',
		name : 'updatePassPanel',
		defaultType : 'textfield',
		fieldDefaults : {
			labelAlign : 'right',
			labelWidth : 100,
			anchor : '99%'
		},
		frame : false,
		bodyStyle : 'padding:10 10',
		items : [ {
			inputType:'password',
			fieldLabel : '<font color="red">*</font>&nbsp;原密码',
			name : 'artunionContext.params.ypass',
			id : 'ypassId',
			allowBlank : false
		},{
			inputType:'password',
			fieldLabel : '<font color="red">*</font>&nbsp;新密码',
			name : 'artunionContext.params.xpass',
			id : 'xpassId',
			allowBlank : false,
			validator : function(value) {
						 if (value.length<6) {
							return "新密码长度必须大于等于6";
						} else {
							return true;
						}
					}
		},{
			inputType:'password',
			fieldLabel : '<font color="red">*</font>重复密码',
			name : 'artunionContext.params.cfpass',
			id : 'cfpassId',
			allowBlank : false
		},{
			xtype : "hiddenfield",
			id : "updatePassPanel"
		} ]
	});
	
		updatePassWindow = new Ext.Window( {
		layout : 'fit',
		width : 400,
		height : 200,
		resizable : false,
		draggable : true,
		closeAction : 'hide',
		title : '<span class="">修改密码</span>',
		titleCollapse : true,
		modal : true,
		maximizable : false,
		buttonAlign : 'right',
		border : false,
		animCollapse : true,
		closable : false,
		pageY : 20,
		pageX : document.body.clientWidth / 2 - 420 / 2,
		constrain : true,
		items : [ updatePassPanel ],
		buttons : [{
							text : '保存',
							iconCls : 'acceptIcon',
							id : 'ck_button_save',
							handler : function() {
							 updatePass(); // 保存或修改仪器设备分类
							}
						},  
						{
							text : '关闭',
							iconCls : 'deleteIcon',
							handler : function() {
							updatePassWindow.close();
							}
						}],
		listeners : {
			"show" : function() {
				//设置光标
		Ext.getCmp('ypassId').focus(false, 1000);

	}
}
	});
updatePassWindow.show();
function updatePass(){
	var url="userManager/user!updatePassword.action";

	var param = {
	   "artunionContext.params.ypass":Ext.getCmp('ypassId').getValue(),
		"artunionContext.params.xpass" : Ext.getCmp('xpassId').getValue(),
		"artunionContext.params.cfpass" : Ext.getCmp('cfpassId').getValue()
		
		
	};
	// 清空全局密码标记
	var form = Ext.getCmp("updatePassPanelId").getForm();
	if (form.isValid()) {
		Ext.getCmp('ck_button_save').setDisabled(true);
		CKGobal.ajax({
					url : url,
					params : param,
					success : function(response, options, resultJSON) {
						// 信息回馈
						Ext.MessageBox.alert('提示', "操作成功", function() {
								});
						// 关闭窗口
						updatePassWindow.close();
					
					},
					enableArray : [Ext.getCmp('ck_button_save')]
				});

	}
		
		
	}
	
	
}




	
