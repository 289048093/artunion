/**
 * 
 */
package com.artunion.action.user.student;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.artunion.BaseAction;
import com.artunion.service.user.student.StudentService;
import com.artunion.util.StringUtil;
import com.artunion.vo.StudentVO;

/**
 * 学生action
 * 
 * @author LiZhao
 * 
 */
@Controller
@Scope("prototype")
@ParentPackage("artunion-default")
@Namespace("/studentManager")
@Results( { @Result(name = "success", type = "dispatcher", location = "/index.jsp") })
public class StudentAction extends BaseAction<StudentVO> {

    /**
     * 
     */
    private static final long serialVersionUID = 5914787150673674388L;
    /**
     * 学生service
     */
    @Resource
    private StudentService studentService;

    @Action("/student")
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
        studentService.addStudent(artunionContext);
        return JSON;
    }
    
    @Override
    public void validate() {
        // TODO Auto-generated method stub
        super.validate();
    }
}
