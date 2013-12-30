/**
 * 
 */
package com.artunion.action.user.teacher;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.artunion.BaseAction;
import com.artunion.service.user.teacher.TeacherService;
import com.artunion.util.StringUtil;
import com.artunion.vo.TeacherVO;

/**
 * @author LiZhao
 *
 */
@Controller
@Scope("prototype")
@ParentPackage("artunion-default")
@Namespace("/teacherManager")
public class TeacherAction extends BaseAction<TeacherVO> {
    
    
    /**
     * 
     */
    private static final long serialVersionUID = -7875238607731495985L;
    
    @Resource
    private TeacherService teacherService;

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
        teacherService.addTeacher(artunionContext);
        return JSON;
    }

}
