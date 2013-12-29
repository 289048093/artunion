package com.artunion.action.menu;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.artunion.BaseAction;
import com.artunion.service.menu.MenuService;
import com.artunion.vo.MenuVO;
@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@ParentPackage("artunion-default")
@Namespace("/menuManager")
@Results( { @Result(name = "success", type = "dispatcher", location = "/index.jsp") ,
	@Result(name = "jump", type = "dispatcher", location = "/jump.jsp")})
public class MenuAction extends BaseAction<MenuVO>{
	@SuppressWarnings("unused")
	@Resource
	private MenuService menuService;

	@Action("/menu")
	public String execute() throws Exception {

		return INPUT;
	}
}
