/**
 * 
 */
package com.artunion.service.user.student;

import java.sql.SQLException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.artunion.ArtUnionContext;
import com.artunion.dao.StudentDAO;
import com.artunion.entity.StudentEntity;
import com.artunion.entity.UserEntity;
import com.artunion.service.user.UserService;
import com.artunion.util.Constant;
import com.artunion.util.StringUtil;
import com.artunion.vo.StudentVO;

/**
 * @author LiZhao
 *
 */
@Service("studentService")
public class StudentService extends UserService {

    /**
     * studentDAO
     */
    @Resource
    private StudentDAO studentDAO;
    
    /**
     * 学生注册
     * @param artunionContext
     * @throws SQLException
     */
    public void addStudent(ArtUnionContext<StudentVO> artunionContext) throws SQLException {
        String username = artunionContext.getVo().getUsername();
        UserEntity ue = studentDAO.findUserByUsername(username);
        if(ue!=null){
            artunionContext.addErrorMsg("用户名已被注册，请更换的用户名");
            return;
        }
        StudentEntity se = new StudentEntity();
        se.setPassword(StringUtil.encrypt(username, artunionContext.getVo().getPassword()));
        BeanUtils.copyProperties(artunionContext.getVo(), se);
        se.setStatus(Constant.USER_STATE_NORMAL);
        se.setAddDate(new Date());
        studentDAO.insert(se);
        artunionContext.addSuccessMsg("注册成功");
    }

}
