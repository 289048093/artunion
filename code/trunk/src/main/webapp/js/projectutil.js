/**
 * 获取service地址
 * 
 * @param {}
 *            serviceIdentity
 * @param {}
 *            checkCommand
 * @return {}
 */
function graphPrint(id, checkCommand, supportGraph) {
	if (!supportGraph) {
		return;
	}
	var form = jq("<form method='post' style='display:none' target='_blank' action='rrdtoolManager/rrdtool!redirectToGraphPage_.action'><input name='serviceId' value='"
			+ id
			+ "'></input><input name='checkCommand' value='"
			+ checkCommand + "'></input></form>");
	form.appendTo("body");
	form.submit();
}

/**
 * 转换
 */
function dateFormat(value) {
	if (null != value) {
		return Ext.Date.format(new Date(value), 'Y-m-d H:i:s');
	} else {
		return null;
	}
}
/**
 * 第一个参数是json的字符串 第二个是关键字
 * 
 * @param {}
 *            allUserstr
 * @param {}
 *            realname
 * @param {}
 *            username
 * @return {}
 */
function quickQueryUser(allUserstr, keyWord) {
	// 设置匹配的增则表达式
	var realname = keyWord;
	var pattern = new RegExp('{[^{]*realname":"[^"]*' + realname + '[^}]*}',
			"ig");
	var matcher = allUserstr.match(pattern);
	// 如果没找到就返回null
	if (matcher == null || matcher == "undefined" || matcher.length == 0) {
		return null;
	}
	var result = "";
	result += "([";
	for (var i = 0; i < matcher.length; i++) {
		result += matcher[i] + ",";
	}
	// 去掉最后一个逗号
	result = result.replace(/,$/, "");
	result += "])";
	return eval(result);
}

/**
 * 替换要删除的记录窗口
 * 
 * @param {}
 *            data
 */
function showDeleteDataWindow(data) {
	data = data || {};
	var newFieldLabel = data.currentLabel || '替换值';
	var oldFieldLabel = data.replaceLabel || '当前值';
	var title = data.title || '删除';
	var headInfo = data.headInfo || '';
	var valueField = data.valueField || 'id';
	var displayField = data.displayField || 'name';
	var buildFlag = data.buildin || 'buildin';
	var id = data.id || '';
	var name = data.name || '';
	var url = data.url;
	var store = new Ext.data.Store({
				fields : [],
				data : []
			});
	if (!Ext.isEmpty(data.store)) {
		store.loadRecords(data.store.getRange());
		store.removeAt(store.find('id', id));// 去除当前记录
	}
	var deleteDataWindow = new Ext.Window({
		title : title,
		width : 300,
		height : 200,
		resizable : false,
		modal : true,
		autoShow : true,
		layout : 'fit',
		items : new Ext.form.Panel({
					fieldDefaults : {
						labelAlign : 'right',
						labelWidth : 60,
						anchor : '99%'
					},
					frame : false,
					bodyStyle : 'padding:10 10',
					items : [{
								html : headInfo,
								border : 0,
								bodyStyle : 'padding:0 0 20 0'
							}, {
								xtype : "textfield",
								fieldLabel : oldFieldLabel,
								value : name,
								disabled : true,
								labelWidth : 50
							}, {
								xtype : 'combobox',
								fieldLabel : newFieldLabel,
								labelWidth : 50,
								displayField : displayField,
								valueField : valueField,
								editable : false,
								emptyText : '--请选择--',
								store : store,
								queryMode : 'local',
								allowBlank : false,
								listeners : {
									beforerender : function() {
										if (this.store.count() == 0) {
											this.emptyText = '没有可选择的数据';
										}
									}
								}
							}]
				}),
		buttons : [{
			text : '确定',
			listeners : {
				click : function(v) {
					var window = v.up().up();
					var panel = window.items.first();
					var combobox = panel.items.last();
					if (combobox.isValid()) {
						CKGobal.ajax({
							url : url,
							method : 'post',
							async : false,
							params : {
								'artunionContext.params.id' : id,
								'artunionContext.params.replaceId' : combobox
										.getValue()
							},
							autoMsgTip : false,
							success : function(response, options, resultJSON,
									havaMsgFlag) {
								if (resultJSON.artunionContext.msgList.length == 0) {
									Ext.MessageBox.alert('提示', "操作成功");
									// 刷新列表
									data.store.load();
									window.close();
									return;
								}
								for (var index in resultJSON.artunionContext.msgList) {
									Ext.MessageBox
											.alert(
													'提示',
													resultJSON.artunionContext.msgList[index]);
								}
								panel.getForm().reset();
							}
						});
					}
				}
			}
		}, {
			text : '取消',
			listeners : {
				click : function(v) {
					v.up().up().close();
				}
			}
		}]
	});
}
