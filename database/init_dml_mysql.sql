-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user`(ID_,ADD_TIME_,PASSWORD_,REALNAME_,USERNAME_,SEX_,STATUS_) VALUES ('1', '2013-10-23 18:42:46', 'a66abb5684c45962d887564f08346e8d', '系统管理员', 'admin','1', '1');

-- ----------------------------
-- Records of tb_role
-- ----------------------------


INSERT INTO `tb_role`(ID_,ADD_TIME_,DESC_,NAME_,PERSISTENCE_) VALUES ('1', '2013-10-12 10:27:04', '', '管理员', 1);
INSERT INTO `tb_role`(ID_,ADD_TIME_,DESC_,NAME_,PERSISTENCE_) VALUES ('2', '2013-10-12 10:27:11', '', '设备管理员', 1);
INSERT INTO `tb_role`(ID_,ADD_TIME_,DESC_,NAME_,PERSISTENCE_) VALUES ('3', '2013-10-12 10:27:35', '', '普通用户', 1);



-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu`(ID_,DESC_,NAME_,REGEX_,URL_,PARENT_MENU_ID_,ICON_) VALUES ('1', null, '门户菜单', null, null, null, null);
INSERT INTO `tb_menu`(ID_,DESC_,NAME_,REGEX_,URL_,PARENT_MENU_ID_,ICON_) VALUES ('2', null, '管理菜单', null, null, null, null);
INSERT INTO `tb_menu`(ID_,DESC_,NAME_,REGEX_,URL_,PARENT_MENU_ID_,ICON_) VALUES ('3', '前台页面菜单', '首页', '(/artunion/?)?(/index.jsp)?', 'index.jsp', '1', 'images/icons/icon_index.png');
-- ----------------------------
-- Records of tb_rights
-- ----------------------------
INSERT INTO `tb_rights`(ID_,NAME_,URL_,DESC_,SUPER_RIGHTS_ID_) VALUES ('1', '信息管理', null, '父菜单', null);
INSERT INTO `tb_rights`(ID_,NAME_,URL_,DESC_,SUPER_RIGHTS_ID_) VALUES ('125', '用户添加', 'userManager/user!addUser.action', '用户add标识', '34');
INSERT INTO `tb_rights`(ID_,NAME_,URL_,DESC_,SUPER_RIGHTS_ID_) VALUES ('126', '用户修改', 'userManager/user!initUpdateUser.action', '用户update标识', '34');
INSERT INTO `tb_rights`(ID_,NAME_,URL_,DESC_,SUPER_RIGHTS_ID_) VALUES ('127', '用户删除', 'userManager/user!deleteUser.action', '用户delete标识', '34');
INSERT INTO `tb_rights`(ID_,NAME_,URL_,DESC_,SUPER_RIGHTS_ID_) VALUES ('128', '角色添加', 'roleManager/role!addRole.action', '角色add标识', '35');
INSERT INTO `tb_rights`(ID_,NAME_,URL_,DESC_,SUPER_RIGHTS_ID_) VALUES ('129', '角色复制', 'roleManager/role!addCopyRole.action', '角色copy标识', '35');
INSERT INTO `tb_rights`(ID_,NAME_,URL_,DESC_,SUPER_RIGHTS_ID_) VALUES ('130', '角色删除', 'roleManager/role!deleteRoles.action', '角色delete标识', '35');
INSERT INTO `tb_rights`(ID_,NAME_,URL_,DESC_,SUPER_RIGHTS_ID_) VALUES ('131', '角色人员管理', 'roleManager/role!initRoleUsers.action', '角色manager标识', '35');
INSERT INTO `tb_rights`(ID_,NAME_,URL_,DESC_,SUPER_RIGHTS_ID_) VALUES ('132', '角色授权', 'roleManager/role!addRightsAndModule.action', '角色授权标识', '35');
INSERT INTO `tb_rights`(ID_,NAME_,URL_,DESC_,SUPER_RIGHTS_ID_) VALUES ('134', '权限添加', 'rightsManager/rights!insert.action', '权限添加标识', '36');
INSERT INTO `tb_rights`(ID_,NAME_,URL_,DESC_,SUPER_RIGHTS_ID_) VALUES ('135', '权限修改', 'rightsManager/rights!initUpdate.action', '权限修改标识', '36');
INSERT INTO `tb_rights`(ID_,NAME_,URL_,DESC_,SUPER_RIGHTS_ID_) VALUES ('136', '权限删除', 'rightsManager/rights!delete.action', '权限删除标识', '36');
INSERT INTO `tb_rights`(ID_,NAME_,URL_,DESC_,SUPER_RIGHTS_ID_) VALUES ('137', '权限批量删除', 'rightsManager/rights!deleteMulti.action', '权限批量删除标识', '36');
-- ----------------------------
-- Table structure for tb_property
-- ----------------------------
DROP TABLE IF EXISTS `tb_property`;
CREATE TABLE `tb_property` (
  `ID_` bigint(20) NOT NULL AUTO_INCREMENT,
  `DEFAULTVALUE_` varchar(255) DEFAULT NULL,
  `DESC_` varchar(255) DEFAULT NULL,
  `ERRORMSG_` varchar(255) DEFAULT NULL,
  `KEY_` varchar(255) NOT NULL,
  `REGEX_` varchar(255) DEFAULT NULL,
  `TYPE_` varchar(20) DEFAULT NULL,
  `VALUE_` varchar(255) NOT NULL,
  PRIMARY KEY (`ID_`),
  UNIQUE KEY `KEY_` (`KEY_`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_property
-- ----------------------------
INSERT INTO `tb_property` VALUES ('3', '0', '是否开启邮件发送功能', '值必须为0或1(0代表否，1代表是)', 'email_enable', '^[01]$', 'Boolean', '1');
INSERT INTO `tb_property` VALUES ('4', 'xxx@xx.com', '邮件服务器用户名', '', 'email_username', '.*', 'String', '52673406@qq.com');
INSERT INTO `tb_property` VALUES ('5', 'xxxx', '邮件服务器密码', '', 'email_password', '.*', 'Password', 'chao123jing');
INSERT INTO `tb_property` VALUES ('6', 'smtp.xx.com', '邮件服务器地址', '', 'email_host', '.*', 'String', 'smtp.qq.com');
INSERT INTO `tb_property` VALUES ('7', '25', '邮件服务器端口', '端口号必须为有效整数', 'email_port', '^\\d{2,5}$', 'Integer', '25');
INSERT INTO `tb_property` VALUES ('8', 'xxx@xx.com', '邮件服务器发送地址', '邮箱格式不对，格式必须为xxxx@xx.xx', 'email_from', '[0-9a-zA-Z_\\.]+@\\w+\\.\\w+', 'String', '52673406@qq.com');
INSERT INTO `tb_property` VALUES ('9', '24', '找回密码邮件有效时间(小时)', '时间必须小于1000小时', 'reset_user_password_email_invalid_time', '^\\d{1,3}$', 'Integer', '24');
INSERT INTO `tb_property` VALUES ('11', '0', '是否自动删除无效的预约时间计划', '必须为true或者false', 'db_delete_record_flag', '^[01]$', 'Boolean', '0');
