/**
 * 
 */
package com.artunion.service.user.teacher;

import java.sql.SQLException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.artunion.ArtUnionContext;
import com.artunion.dao.TeacherDAO;
import com.artunion.entity.TeacherEntity;
import com.artunion.entity.UserEntity;
import com.artunion.service.user.UserService;
import com.artunion.util.Constant;
import com.artunion.util.StringUtil;
import com.artunion.vo.TeacherVO;

/**
 * @author LiZhao
 * 
 */
@Service("teacherService")
public class TeacherService extends UserService {

    /**
     * teacherDAO
     */
    @Resource
    private TeacherDAO teacherDAO;

    /**
     * 老师注册
     * 
     * @param artunionContext
     * @throws SQLException
     */
    public void addTeacher(ArtUnionContext<TeacherVO> artunionContext) throws SQLException {
        String username = artunionContext.getVo().getUsername();
        UserEntity ue = teacherDAO.findUserByUsername(username);
        if (ue != null) {
            artunionContext.addErrorMsg("用户名已被注册，请更换的用户名");
            return;
        }
        TeacherEntity te = new TeacherEntity();
        te.setPassword(StringUtil.encrypt(username, artunionContext.getVo().getPassword()));
        BeanUtils.copyProperties(artunionContext.getVo(), te);
        te.setStatus(Constant.USER_STATE_NORMAL);
        te.setAddDate(new Date());
        teacherDAO.insert(te);
        artunionContext.addSuccessMsg("注册成功");
    }

}
