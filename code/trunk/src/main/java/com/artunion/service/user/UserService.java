/**
 * Copyright(c) 2013 ShenZhen CloudKing Technology Co., Ltd.
 * All rights reserved.
 * Created on  Apr 28, 2013  10:58:25 AM
 */
package com.artunion.service.user;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

import com.artunion.ArtUnionContext;
import com.artunion.BaseService;
import com.artunion.LoginedUser;
import com.artunion.dao.ResetUserPasswordDAO;
import com.artunion.dao.RightsDAO;
import com.artunion.dao.UserDAO;
import com.artunion.entity.ResetUserPasswordEntity;
import com.artunion.entity.UserEntity;
import com.artunion.util.Constant;
import com.artunion.util.MailUtil;
import com.artunion.util.StringUtil;
import com.artunion.vo.UserVO;

/**
 * 用户service
 * 
 * @author CloudKing
 */
@SuppressWarnings("unused")
@Service("userService")
public class UserService extends BaseService {
    /**
     * userDAO
     */
    @Resource
    private UserDAO<UserEntity> userDAO;
    @Resource
    private RightsDAO rightsDAO;
    @Resource
    private ResetUserPasswordDAO resetUserPasswordDAO;

    /**
     * 注销
     * 
     * @param artunionContext
     * @exception exc
     */
    public void logout(ArtUnionContext<UserVO> artunionContext) {

    }

    /**
     * 登陆
     * 
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public void login(ArtUnionContext<UserVO> artunionContext) throws SQLException {
        // 得到用户输入的姓名、密码及验证码
        String username = artunionContext.getVo().getUsername();
        UserEntity loginUser = null;
        String password = artunionContext.getVo().getPassword();
        String checkCode = artunionContext.getStringParam("checkCode");
        // 得到常量验证码
        String verifyCode = artunionContext.getStringParam(Constant.VERIFY_CODE);
        // 首先验证 验证码 验证码不通过直接返回
        if (!checkCode.equals(verifyCode)) {
            artunionContext.addErrorMsg("验证码错误");
            return;
        }
        String md5pwd = StringUtil.encrypt(username, password);
        // 用户名与加密后的密码去数据库中匹配
        loginUser = userDAO.findUserByUsernameAndPassword(username, md5pwd);

        LoginedUser loginUserVO = new LoginedUser();
        BeanUtils.copyProperties(loginUser, loginUserVO);
        artunionContext.addParam(Constant.LOGINED_USER, loginUserVO);

        artunionContext.setLoginedUser(loginUserVO);
        artunionContext.addSuccessMsg("登录成功");

    }

    /**
     * 注册
     * 
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public void addUser(ArtUnionContext<UserVO> artunionContext) throws SQLException {
        // 判断注册用户名是否已经存在
        UserEntity userEntity = userDAO.findUserByUsername(artunionContext.getVo().getUsername());
        if (userEntity == null) {

            userEntity = new UserEntity();
            BeanUtils.copyProperties(artunionContext.getVo(), userEntity);
            userEntity.setAddDate(new Date());
            userEntity.setStatus(Constant.USER_STATE_NORMAL);
            userEntity.setPassword(StringUtil.encrypt(artunionContext.getVo().getUsername(), artunionContext.getVo()
                    .getPassword()));
            userDAO.insert(userEntity);
            artunionContext.addSuccessMsg("注册成功");
        } else {
            artunionContext.addErrorMsg("用户名已存在");
        }
    }

    /**
     * @param artunionContext
     */
    public void query(ArtUnionContext<UserVO> artunionContext) {
        // TODO Auto-generated method stub

    }

    /**
     * 找回密码
     */
    public void retrievePwd(ArtUnionContext<UserVO> artunionContext) {
        // TODO Auto-generated method stub

    }

    /**
     * 用户名校验
     * 
     * @throws SQLException
     */
    public void initRegister(ArtUnionContext<UserVO> artunionContext) throws SQLException {
        // 用户名判断
        String username = artunionContext.getVo().getUsername();
        UserEntity userEntity = userDAO.findUserByUsername(username);
        artunionContext.addParam("usernameExist", userEntity != null);
    }


    /**
     * 验证邮箱是否被使用
     * 
     * @param artunionContext
     * @throws SQLException
     */
    public void findByEmail(ArtUnionContext<UserVO> artunionContext) throws SQLException {
        String email = artunionContext.getVo().getEmail();
        UserEntity userEntity = userDAO.findUserByEmail(email);
        artunionContext.addParam("emailExist", userEntity != null);
    }

    /**
     * 重置密码（邮件发送重置连接）
     * 
     * @param artunionContext
     * @throws SQLException
     */
    public void addResetPassword(ArtUnionContext<UserVO> artunionContext) throws SQLException {
        String checkCode = artunionContext.getStringParam("checkCode");
        // 验证码
        String verifyCode = artunionContext.getStringParam(Constant.VERIFY_CODE);
        if (!checkCode.equals(verifyCode)) {
            artunionContext.addErrorMsg("验证码错误");
            return;
        }
        String email = artunionContext.getVo().getEmail();
        UserEntity userEntity = userDAO.findUserByEmail(email);
        if (userEntity == null) {
            artunionContext.addErrorMsg("该邮箱未注册用户");
            return;
        }
        // 邮件发送 密码重置
        String key = UUID.randomUUID().toString();
        ResetUserPasswordEntity rupe = new ResetUserPasswordEntity();
        rupe.setKey(key);
        rupe.setUser(userEntity);
        rupe.setAddDate(new Date());
        resetUserPasswordDAO.insert(rupe);
        String basePath = artunionContext.getStringParam("basePath");
        MailUtil
                .sendMail(
                        "用户找回密码",
                        String
                                .format(
                                        "%1$s您好，您帐号：%2$s密码找回成功，请点击下面的密码修改按钮设置新密码 \r\n <br><br><center><a target='_blank' href='%3$s'><b>密码修改</b></a></center> <br>\r\n 或者复制下面的连接地址在浏览器中打开：<br><a href='%3$s' target='_blank'>%3$s</a> <br>\r\n 邮件24小时内有效",
                                        userEntity.getRealname(),
                                        userEntity.getUsername(),
                                        basePath
                                                + "userManager/user!initUpdateResetPassword.action?artunionContext.params.key="
                                                + key), email, true);
    }

    public void initUpdateResetPassword(ArtUnionContext<UserVO> artunionContext) throws SQLException {
        String key = artunionContext.getStringParam("key");
        ResetUserPasswordEntity rupe = resetUserPasswordDAO.findByKey(key);
        if (rupe == null) {
            artunionContext.addErrorMsg("链接已失效，请重新找回");
            return;
        }
        BeanUtils.copyProperties(rupe.getUser(), artunionContext.getVo());
    }

    /**
     * 密码重置
     * 
     * @param artunionContext
     * @throws SQLException
     */
    public void updateResetPassword(ArtUnionContext<UserVO> artunionContext) throws SQLException {
        String key = artunionContext.getStringParam("key");
        ResetUserPasswordEntity rupe = resetUserPasswordDAO.findByKey(key);
        if (rupe == null) {
            artunionContext.addErrorMsg("链接已失效，请重新找回");
            return;
        }
        UserEntity user = rupe.getUser();
        String newPwd = artunionContext.getVo().getPassword();
        String md5pwd = StringUtil.encrypt(user.getUsername(), newPwd);
        user.setPassword(md5pwd);
        userDAO.update(user);
        resetUserPasswordDAO.delete(rupe);
        artunionContext.addSuccessMsg("修改成功");
    }

    /**
     * 个人中心
     * 
     * @param artunionContext
     * @throws SQLException
     * @throws BeansException
     */
    public void center(ArtUnionContext<UserVO> artunionContext) throws BeansException, SQLException {
        LoginedUser loginedUser = (LoginedUser) ServletActionContext.getRequest().getSession().getAttribute(
                Constant.LOGINED_USER);
        if (loginedUser == null) {
            artunionContext.addErrorMsg("您还未登录，请先登录");
            return;
        }
        UserEntity user = userDAO.get(loginedUser.getId());
        BeanUtils.copyProperties(user, artunionContext.getVo());
    }

    /**
     * 个人中心修改
     * 
     * @param artunionContext
     * @throws SQLException
     */
    public void updateCenter(ArtUnionContext<UserVO> artunionContext) throws SQLException {
        // 通过id从数据库判断是否有该用户
        UserEntity userEntity = userDAO.get(artunionContext.getVo().getId());
        if (userEntity == null) {
            artunionContext.addErrorMsg("用户不存在，请刷新重试");
            return;
        }
        if (artunionContext.getVo().getHeadPic() != null) {
            userEntity.setHeadPic(artunionContext.getVo().getHeadPic());
        }
        userEntity.setSex(artunionContext.getVo().getSex());
        userEntity.setAddr(artunionContext.getVo().getAddr());
        userEntity.setEmail(artunionContext.getVo().getEmail());
        userEntity.setRealname(artunionContext.getVo().getRealname());
        userEntity.setMobilePhone(artunionContext.getVo().getMobilePhone());
        userEntity.setTelPhone(artunionContext.getVo().getTelPhone());
        userDAO.insert(userEntity);
        BeanUtils.copyProperties(userEntity, artunionContext.getVo());
        artunionContext.addSuccessMsg("更新成功");
    }

    public void updatePassword(ArtUnionContext<UserVO> artunionContext) throws SQLException {
        Long id = artunionContext.getLoginedUser().getId();
        UserEntity user = userDAO.get(id);
        if (user == null) {
            artunionContext.addErrorMsg("用户不存在");
            return;
        }
        if (!user.getPassword().equals(StringUtil.encrypt(user.getUsername(), artunionContext.getVo().getPassword()))) {
            BeanUtils.copyProperties(user, artunionContext.getVo());
            artunionContext.addErrorMsg("原密码错误");
            return;
        }
        user.setPassword(StringUtil.encrypt(user.getUsername(), artunionContext.getStringParam("newPassword")));
        userDAO.update(user);
        artunionContext.addSuccessMsg("密码修改成功");
    }
}
