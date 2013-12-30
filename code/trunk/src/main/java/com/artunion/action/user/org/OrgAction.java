/**
 * 
 */
package com.artunion.action.user.org;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.artunion.BaseAction;
import com.artunion.service.user.org.OrgService;
import com.artunion.util.StringUtil;
import com.artunion.vo.OrgVO;

/**
 * 机构
 * @author LiZhao
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("artunion-default")
@Namespace("/teacherManager")
public class OrgAction extends BaseAction<OrgVO> {

    /**
     * 
     */
    private static final long serialVersionUID = -858808599188400643L;
    
    @Resource
    private OrgService orgService;
    
    @Action("/teacher")
    public String execute() throws Exception {
        // TODO Auto-generated method stub
        return INPUT;
    }

    /**
     * 注册
     * @throws SQLException sqlExc
     */
    public String register() throws SQLException{
        if (StringUtil.isBlank(artunionContext.getVo().getUsername())) {
            artunionContext.addErrorMsg("用户名不能为空");
        }
        if (StringUtil.isBlank(artunionContext.getVo().getPassword())) {
            artunionContext.addErrorMsg("密码不能为空!");
        }
        if (StringUtil.isBlank(artunionContext.getVo().getRealname())) {
            artunionContext.addErrorMsg("姓名不能为空!");
        }
        orgService.addOrg(artunionContext);
        return JSON;
    }

}
