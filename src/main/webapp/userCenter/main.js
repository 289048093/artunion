Ext.Loader.setConfig({
			enabled : true
		});
Ext.Loader.setPath('Ext.ux', 'extjs/ux');

var tabpanel;
Ext.onReady(function() {
	// 标签panel
	tabpanel = Ext.create("Ext.tab.Panel", {
				layout : "fit",
				resizeTabs : true,
				enableTabScroll : true,
				width : 600,
				height : 250,
				width : 400,
				height : 400,
				activeTab : 0,
				defaults : {
					autoScroll : true,
					autoScroll : true
				},
				items : [{
							title : "信息面板",
							id : "tab_dashboard",
							contentTitle : "深圳大学城开放实验室服务平台->信息面板",
							closable : false,
							html : "欢迎进入个人中心！！！"
						}],
				plugins : Ext.create('Ext.ux.TabCloseMenu', {
							closeTabText : '关闭当前',
							closeOthersTabsText : '关闭其他',
							closeAllTabsText : '关闭所有',
							extraItemsTail : ['-', {
										text : '刷新页面',
										handler : function(item) {
											reloadCurrentTab(currentItem);

										}
									}],
							listeners : {
								aftermenu : function() {
									currentItem = null;
								},
								beforemenu : function(menu, item) {
									currentItem = item;
								}
							}
						})
			});

	// 树的panel
	var treeStore = Ext.create("Ext.data.TreeStore", treeNodes);

	// 树的panel
	// var treeStore = Ext.create("Ext.data.TreeStore", jsonTree);
	var treePanel = Ext.create("Ext.tree.Panel", {
				id : "tree-panel",
				title : "系统导航",
				region : "north",
				split : true,
				rootVisible : false,// root否可见
				autoScroll : true,
				store : treeStore,
				listeners : {
					itemclick : function(node, record) {
						if (record.raw.noClick) {
							return;
						} else {
							addTab(record.raw.text, record.raw.contentTitle,
									record.raw.tabId, record.raw.url,
									record.raw.iconCls);
						}
					}
				}

			});
	treePanel.expandAll();

	// 主要的布局
	Ext.create("Ext.container.Viewport", {
				layout : "border",
				items : [new Ext.panel.Panel({
									region : "north",
									contentEl : "north",
									border : false,
									title : "深圳大学城开放实验室服务平台",
									layout : "fit",
									height : 85
								}), {
							layout : "fit",
							region : "south",
							html : "深圳大学城开放实验室服务平台 - V.1.0.0"
						}, {
							collapsible : true,
							split : true,
							region : "west",
							width : 300,
							maxWidth : 300,
							title : "系统导航",
							layout : "accordion",
							layoutConfig : {
								animate : true,
								activeOnTop : true
							},
							items : [treePanel]
						}, {
							id : "content-panel",
							region : "center",
							layout : "fit",
							title : "当前位置：深圳大学城开放实验室服务平台->用户信息",
							items : [tabpanel]
						}],
				renderTo : Ext.getBody()
			});

	// 关闭
	var closeButton = new Ext.Button({
				iconCls : "cancel_25_25Icon",
				iconAlign : "left",
				scale : "medium",
				autoWidth : true,
				tooltip : "<span style='font-size:12px'>注销用户</span>",
				pressed : true,
				arrowAlign : "right",
				renderTo : "closeDiv",
				handler : function() {
					logout();
				}
			});
	// 关闭
	var indexButton = new Ext.Button({
				iconCls : "indexIcon",
				iconAlign : "left",
				scale : "medium",
				autoWidth : true,
				tooltip : "<span style='font-size:12px'>返回首页</span>",
				pressed : true,
				arrowAlign : "right",
				renderTo : "indexButton",
				handler : function() {
					location.href = "index.jsp";
				}
			});

	/**
	 * 创建节点
	 * 
	 * @param {}
	 *            closable
	 */
	function addTab(title, contentTitle, tabId, url, iconCls) {
		var tab = tabpanel.getComponent(tabId);
		contentTitle = "当前位置：" + contentTitle;
		if (tab) {
			reloadCurrentTab({
						id : tabId
					});
		}
		if (!tab) {
			if (Ext.isEmpty(iconCls)) {
				iconCls = "";
			}
			tabpanel.add({
				title : title,
				id : tabId,
				iconCls : iconCls,
				contentTitle : contentTitle,
				closable : true,
				layout : "fit",
				listeners : {
					activate : function() {
						Ext.getCmp("content-panel").setTitle(contentTitle)
					},
					beforeclose : function(tab, eOpts) {
						Ext.getCmp("content-panel").setTitle(tab
								.previousSibling().contentTitle);
						return true;
					},
					close : function(tab, eOpts) {
						tabpanel.setActiveTab(tab.previousSibling());
					}
				},
				html : "<iframe id=cloudKing_"
						+ tabId
						+ " scrolling='auto' frameborder='0' width='100%' height='100%' src="
						+ url + "></iframe>"
			}).show();
		}
		tabpanel.setActiveTab(tab);
	}
	if (linkflag && menuTree[linkflag]) {
		addTab(menuTree[linkflag].text, menuTree[linkflag].contentTitle,
				menuTree[linkflag].tabId, menuTree[linkflag].url,
				menuTree[linkflag].iconCls);
	}

	/**
	 * 获取样式
	 */
	function getIcoCls(url) {
		var url = url.substring(1);
		var iconCls = CKGobal.findJSON(moduleTree, "\"url\":\"" + url + "\"").iconCls;
		if (Ext.isEmpty(iconCls)) {
			iconCls = "";
		}
		return iconCls;
	}
	/**
	 * 获取Title
	 */
	function getTitle(url) {
		var url = url.substring(1);
		var text = CKGobal.findJSON(moduleTree, "\"url\":\"" + url + "\"").text;
		if (Ext.isEmpty(text)) {
			text = "";
		}
		return "<span style='font-weight:normal'>" + text + "</span>";
	}
	/**
	 * 注销用户
	 */
	function logout() {
		parent.location.href = "userManager/user!logout.action";
	}

	/**
	 * 刷新当前tab
	 */
	function reloadCurrentTab(currentItem) {
		var frameName = 'cloudKing_' + currentItem.id;
		var url = jq("#" + frameName).attr("src");
		jq("#" + frameName).attr("src", url);
	}

});
