package com.artunion.action.rights;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.artunion.BaseAction;
import com.artunion.service.rights.RightsService;
import com.artunion.vo.RightsVO;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
@ParentPackage("artunion-default")
@Namespace("/rightsManager")
@Results( { @Result(name = "success", type = "dispatcher", location = "/index.jsp") ,
	@Result(name = "jump", type = "dispatcher", location = "/jump.jsp")})
public class RightsAction extends BaseAction<RightsVO> {
	
	@SuppressWarnings("unused")
	@Resource
	private RightsService rightsService;
	
	
	@Action("/rights")
	public String execute() throws Exception {

		return INPUT;
	}

}
