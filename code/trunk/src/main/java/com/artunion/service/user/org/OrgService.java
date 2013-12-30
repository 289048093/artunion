/**
 * 
 */
package com.artunion.service.user.org;

import java.sql.SQLException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.artunion.ArtUnionContext;
import com.artunion.dao.OrgDAO;
import com.artunion.entity.OrgEntity;
import com.artunion.entity.UserEntity;
import com.artunion.service.user.UserService;
import com.artunion.util.Constant;
import com.artunion.util.StringUtil;
import com.artunion.vo.OrgVO;

/**
 * @author LiZhao
 * 
 */
@Service("orgService")
public class OrgService extends UserService {

    /**
     * teacherDAO
     */
    @Resource
    private OrgDAO orgDAO;

    /**
     * 老师注册
     * 
     * @param artunionContext
     * @throws SQLException
     */
    public void addOrg(ArtUnionContext<OrgVO> artunionContext) throws SQLException {
        String username = artunionContext.getVo().getUsername();
        UserEntity ue = orgDAO.findUserByUsername(username);
        if (ue != null) {
            artunionContext.addErrorMsg("用户名已被注册，请更换的用户名");
            return;
        }
        //TODO 验证权限号
//        String authNum = artunionContext.getVo().getAuthNum();
        OrgEntity oe = new OrgEntity();
        oe.setPassword(StringUtil.encrypt(username, artunionContext.getVo().getPassword()));
        BeanUtils.copyProperties(artunionContext.getVo(), oe);
        oe.setStatus(Constant.USER_STATE_NORMAL);
        oe.setAddDate(new Date());
        orgDAO.insert(oe);
        artunionContext.addSuccessMsg("注册成功");
    }

}
