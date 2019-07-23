package xyz.yuelai.service.impl;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.yuelai.dao.IUserDAO;
import xyz.yuelai.pojo.domain.UserDO;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.service.IAuthService;
import xyz.yuelai.util.Constant;
import xyz.yuelai.util.EncryptUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author 李泽众
 * @date 2019/7/12-12:38
 */
@Log4j
@Service
public class AuthServiceImpl implements IAuthService {

    private IUserDAO userDAO;

    public AuthServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public ResponseDTO login(String username, String password) {

        int code;
        String msg;

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            code = Constant.CODE_ERROR_PARAMS;
            msg = "用户名或密码非法！";
        } else if (username.length() > Constant.LEGAL_STRING_LENGTH ||
                password.length() > Constant.LEGAL_STRING_LENGTH) {
            code = Constant.CODE_ERROR_PARAMS;
            msg = "用户名或密码长度过长，请保证在" + Constant.LEGAL_STRING_LENGTH + "个字符以内！";

        } else {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {

                subject.login(token);
                code = Constant.CODE_OK;
                msg = "登录成功！";
                boolean hasRole = subject.hasRole("admin");
                boolean permitted = subject.isPermitted("/home");
                System.out.println(permitted);
                System.out.println(hasRole);
            } catch (AuthenticationException e) {
                code = Constant.CODE_UNAUTHENTICATED;
                msg = "用户名或密码不正确！";
                log.error(username + "登录失败！");
            }
        }
        return new ResponseDTO(code, msg);
    }

    @Override
    public ResponseDTO register(String username, String password, String vcode) {
        int code;
        String msg;
        UserDO userDO = null;

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            code = Constant.CODE_ERROR_PARAMS;
            msg = "用户名或密码非法！";
        } else if (username.length() > Constant.LEGAL_STRING_LENGTH ||
                password.length() > Constant.LEGAL_STRING_LENGTH) {
            code = Constant.CODE_ERROR_PARAMS;
            msg = "用户名或密码长度过长，请保证在" + Constant.LEGAL_STRING_LENGTH + "个字符以内！";
        } else if (StringUtils.isEmpty(vcode) || vcode.length() > Constant.LEGAL_STRING_LENGTH) {
            code = Constant.CODE_ERROR_PARAMS;
            msg = "验证码不正确！";
        } else {
            userDO = new UserDO();
            userDO.setUsername(username);
            userDO.setPassword(EncryptUtil.encrypt(password, username));
            userDO.setGmtCreate(Timestamp.valueOf(LocalDateTime.now()));
            userDO.setGmtModified(Timestamp.valueOf(LocalDateTime.now()));
            userDO.setForbidden((byte) 0);
            userDO.setNickname("test");
            userDAO.save(userDO);
            code = Constant.CODE_OK;
            msg = "succeed";
        }
        return new ResponseDTO(code, msg, userDO);
    }
}
