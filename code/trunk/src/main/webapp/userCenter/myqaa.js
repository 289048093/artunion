/*itemclick:function( o, record, item, index, e, eOpts ){
	lert(123);单击事件
}*/

// 全局用户密码保存变量

Ext.Loader.setConfig( {
	enabled : true
});
Ext.Loader.setPath('Ext.ux', 'extjs/ux');

Ext.require( [ 'Ext.grid.*', 'Ext.data.*', 'Ext.ux.RowExpander',
		'Ext.selection.CheckboxModel' ]);

/**
 *
 */
var questionStore;
var addOrUpdateApplyOrderWindow;
var addOrUpdateApplyOrderPanel;

var answerWindow;
var answerPanel;

var timeStore;
var statusStore;

Ext.onReady(function() {

	Ext.QuickTips.init();
	// ##########################################################
		// 数据源存储块 开始
		// ##########################################################

		// 订单列表存储块
		questionStore = Ext
				.create(
						"Ext.data.Store",
						{
							autoLoad : true,
							fields : [ "id","title", "content", "addDate",
									"modifuDate", "isView", "questionerId", "questionerName",
									"expertId", "expertName"],
							pageSize : DEFAULT_EACH_PAGE_DATA,
							proxy : {
								pageParam : "cloudContext.pageInfo.nowPage",
								limitParam : "cloudContext.pageInfo.eachPageData",
								type : "ajax",
								actionMethods : {
									read : 'POST'
								},
								url : "expertQuestionManager/expertQuestion!userCenterShowMyQuestion.action",
								reader : {
									type : "json",
									root : "cloudContext.params.expertQuestions",
									totalProperty : 'cloudContext.pageInfo.dataCount'
								}
							}
						});

		// 搜索栏设备
		timeStore = Ext.create("Ext.data.Store", {
			fields : [ "id", "beginAndEnd" ]
		});

		// 订单状态 Store
		statusStore = Ext.create('Ext.data.Store', {
			fields : [ 'id', 'name' ]

		});

		var applyOrdersGrid = new Ext.grid.Panel(
				{
					layout : "fit",
					//title : parent.getTitle(jq("#requesturlId").val()),
					//iconCls : parent.getIcoCls(jq("#requesturlId").val()),
					multiSelect : true,// 支持多选
					selType : 'rowmodel',// 设置为单元格选择模式Ext.selection.RowModel
					id : "applyOrdersGridId",
					renderTo : Ext.getBody(),
					collapsible : true,
					autoScroll : true,
					store : questionStore,
					ckCollapsed : true,
					toggleCollapse : function() {
						this.collapseTool.disable();
						var items = this.dockedItems;
						for ( var i = 0; i < items.length; i++) {
							if (!Ext.isEmpty(items.get(i).hideFlag)
									&& items.get(i).hideFlag) {
								if (!this.ckCollapsed) {
									items.get(i).hide();
								} else {
									items.get(i).show();
								}
							}
						}
						this.update();
						this.getView().refresh();
						if (this.ckCollapsed) {
							// 设置图标箭头向上
							this.collapseTool.setType("collapse-top");
						} else {
							// 设置图标箭头向下
							this.collapseTool.setType("expand-bottom")
						}
						this.ckCollapsed = !this.ckCollapsed;
						this.collapseTool.enable();
						return this;
					},
					listeners : {
						afterrender : function() {
							// 设置开始的图标箭头向下
							this.collapseTool.setType("expand-bottom");
						}
						
					},

					loadMask : {
						msg : "正在加载数据,请稍等..."
					},
					plugins : [ {
						ptype : 'rowexpander',
						rowBodyTpl : new Ext.XTemplate(
								"<div class='detail_wrapper'>",
								"<div><b>问题内容：</b></div><p/>",
								"<div class='detail_item'>{content}</div>",
								"</div>",
								{
									formatDate : function(dateVal) {
										if (dateVal != null) {
											dateVal = dateVal.replace("T", " ");
										}
										return dateVal;
									}
								})
					} ],
					columns : [
							Ext.create("Ext.grid.RowNumberer"),
							{
								text : "问题主题",
								width : 100,
								dataIndex : "title"
							},
							{
								text : "问题内容",
								width : 380,

								dataIndex : "content"
							},
							{
								text : "提问人",
								width : 80,
								dataIndex : "questionerName"
							},
							{
								text : "提问专家",
								width : 80,
								dataIndex : "expertName"
							},
							{
								header : "操作",
								width : 150,
								align : 'center',
								xtype : 'templatecolumn',
								tpl : '<a style="text-decoration:none;" href="javascript:initAnswer({id});">' 
									+'<img src="extjs/resources/icons/page_white_edit.png" alt="回答" title="解答问题"  class="actionColumnImg" /></a>&nbsp;' +
											'<img src="extjs/resources/icons/page_white_horizontal.png" onclick="parent.window.open(\'expertQuestionManager/expertQuestion!showoneExpert.action?cloudContext.vo.id={id}\')" style="cursor:pointer;" title="浏览"/>'
							}

					],
					dockedItems : [
							{
								xtype : "pagingtoolbar",
								store : questionStore,
								dock : "bottom",
								displayInfo : true,
								items : [
										'-',
										'&nbsp;&nbsp;',
										new Ext.form.field.ComboBox(
												{
													id : 'pagesize_combo',
													hiddenName : 'pagesize',
													typeAhead : true,
													triggerAction : 'all',
													lazyRender : true,
													queryMode : 'local',
													store : new Ext.data.ArrayStore(
															{
																fields : [
																		'value',
																		'text' ],
																data : [
																		[ 10,
																				'20条/页' ],
																		[ 20,
																				'40条/页' ],
																		[ 60,
																				'60条/页' ],
																		[ 80,
																				'80条/页' ],
																		[ 100,
																				'100条/页' ] ]
															}),
													valueField : 'value',
													displayField : 'text',
													value : DEFAULT_EACH_PAGE_DATA,
													editable : false,
													width : 85,
													listeners : {
														"select" : function(
																comboBox) {
															var pageSize = parseInt(comboBox
																	.getValue());
															questionStore.pageSize = pageSize;
															questionStore.currentPage = 1;
															questionStore
																	.load();
														}
													}
												}) ]
							},
							{
								xtype : "toolbar",
								style : 'border-width:0px 1px 1px 1px;background-image: none',
								hideFlag : true,
								hidden : true,
								items : [
										{
											xtype : "textfield",
											fieldLabel : '订单号',
											name : 'searchOrderNum',
											id : "searchOrderNumId",
											labelWidth : 50
										},
										"-",
										{
											text : "搜索",
											icon : "extjs/resources/icons/zoom.png",
											handler : function() {
												query();
											}
										},
										{
											text : "重置",
											icon : "extjs/resources/icons/arrow_rotate_anticlockwise.png",
											handler : function() {
												Ext.getCmp('searchOrderNumId')
														.setValue('');

												// 刷新列表
												query();
											}
										} ]
							} ]
				});

		/**
		 * 布局
		 */
		var viewport = new Ext.Viewport( {
			layout : "fit",
			items : [ applyOrdersGrid ]
		});
		
		answerPanel = new Ext.form.FormPanel( {
			id : 'answerPanel',
			name : 'answerPanel',
			enctype : 'multipart/form-data',
			defaultType : 'textfield',
			fieldDefaults : {
				labelAlign : 'right',
				labelWidth : 70,
				anchor : '99%'
			},
			frame : false,
			bodyStyle : 'padding:10 10',
			items : [ {
				id : 'equipmentId',
				name : 'cloudContext.vo.questionId',
				hidden : true
			},{

				xtype : 'htmleditor',
				id : 'descId',
				name : 'cloudContext.vo.content',
				height : 440,
				anchor : '98%',
				enableAlignments : true,
				enableColors : true,
				enableFont : true,
				enableFontSize : true,
				enableFormat : true,
				enableLinks : true,
				enableLists : true,
				enableSourceEdit : true,

				validator : function(value) {
					if (value.length > 2000) {
						this.setValue(value.substring(0, 2000));
						return "内容的长度必须小于2000";
					} else {
						return true;
					}
				}
			}, {
				xtype : "hiddenfield",
				id : "addOrUpdateEquipmentFlag"
			}, {
				xtype : 'hiddenfield',
				id : 'fileNameId',
				name : 'cloudContext.vo.uploadFileFileName'
			} ]
		});
		
		answerWindow = new Ext.Window( {
			layout : 'fit',
			width : 400,
			height : 450,
			resizable : false,
			draggable : true,
			closeAction : 'hide',
			title : '<span class="">添加仪器设备</span>',
			titleCollapse : true,
			modal : true,
			maximizable : false,
			buttonAlign : 'right',
			border : false,
			animCollapse : true,
			pageY : 20,
			pageX : document.body.clientWidth / 2 - 420 / 2,
			constrain : true,
			items : [answerPanel],
			buttons : [ {
				text : '保存',
				iconCls : 'acceptIcon',
				id : 'ck_button_save',
				handler : function() {
					addAnswer(); // 保存或修改仪器设备
			}
			}, {
				text : '关闭',
				iconCls : 'deleteIcon',
				handler : function() {
					answerWindow.close();
				}
			} ],
			listeners : {
				"show" : function() {
					//设置光标
			if (Ext.getCmp('addOrUpdateEquipmentFlag').getValue() == "add") {
				Ext.getCmp("descId").focus(false, 1000);
			} else {
				Ext.getCmp("descId").focus(false, 1000);
			}
		}
	}
		});
		
		//#### answerWindow end ####

	});

// 日期转换器
function subStringDate(value) {

	if (value == null || value == 'undefined') {
		return "";
	} else {
		return value.substring(0, 10);
	}
}

function query() {
	var params = {
		
		"cloudContext.vo.ordrNum" : Ext.getCmp('searchOrderNumId').getValue()
	};
	questionStore.proxy.extraParams = params;
	questionStore.load();
}
　function icon(val){   

　　　　return '<img src="'+val+'"  style="width:30px; height:30px;"/>'  

　　}  

function initAnswer(id){
		// 修改仪器设备
	Ext.getCmp('addOrUpdateEquipmentFlag').setValue("add");
	answerWindow.setTitle('<span class="">回答问题</span>');
	answerWindow.show();
	Ext.getCmp('ck_button_save').setDisabled(false);
	answerPanel.getForm().reset();
	Ext.getCmp('equipmentId').setValue(id); //id
}

function addAnswer(){
	var url = "expertQuestionAnswerManager/expertQuestionAnswer!addEQA.action"; // 默认为添加模式，如果存在全局的密码，则会替换该url
	var param = {
		"cloudContext.vo.questionId" : Ext.getCmp('equipmentId').getValue(),
		"cloudContext.vo.content" : Ext.getCmp('descId').getValue()
	};
	// 清空全局密码标记
	var form = Ext.getCmp("answerPanel").getForm();
	if (form.isValid()) {
		Ext.getCmp('ck_button_save').setDisabled(true);
		form.submit( {
			clientValidation : true,
			url : url,
			//	params : param,
			success : function(form, action) {
				Ext.Msg.alert('提示信息', "回答成功");
				Ext.getCmp('form-file').setRawValue('');
				// 关闭窗口
			answerWindow.close();
			// 刷新列表
			questionStore.load();
		},
		failure : function(form, action) {
				Ext.Msg.alert('提示信息', "回答成功");
			Ext.getCmp('ck_button_save').setDisabled(false);
			// 关闭窗口
			answerWindow.close();
			// 刷新列表
			questionStore.load();
		},
		enableArray : [ Ext.getCmp('ck_button_save') ]
		});

	}
}