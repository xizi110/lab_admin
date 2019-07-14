package xyz.yuelai.service.impl;

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

@Service
public class AuthServiceImpl implements IAuthService {

    private IUserDAO userDAO;

    public AuthServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public ResponseDTO login(String username, String password) {

        int code ;
        String msg ;

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            code = -1;
            msg = "用户名或密码非法！";
        }else if(username.length() > Constant.LEGAL_STRING_LENGTH ||
                password.length() > Constant.LEGAL_STRING_LENGTH){
            code = -1;
            msg = "用户名或密码长度过长，请保证在" + Constant.LEGAL_STRING_LENGTH + "个字符以内！";

        }else {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                subject.login(token);
                code = 0;
                msg = "succeed: " + subject.isAuthenticated();
            }catch (AuthenticationException e){
                code = -1;
                msg = "failed: " + subject.isAuthenticated();
            }
        }
        return new ResponseDTO(code, msg);
    }

    @Override
    public ResponseDTO register(String username, String password, String vcode) {
        int code ;
        String msg ;
        UserDO userDO = null ;

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            code = -1;
            msg = "用户名或密码非法！";
        }else if(username.length() > Constant.LEGAL_STRING_LENGTH ||
                password.length() > Constant.LEGAL_STRING_LENGTH){
            code = -1;
            msg = "用户名或密码长度过长，请保证在" + Constant.LEGAL_STRING_LENGTH + "个字符以内！";
            throw new NullPointerException("空值");
        }else if(StringUtils.isEmpty(vcode) || vcode.length() > Constant.LEGAL_STRING_LENGTH){
            code = -1;
            msg = "验证码不正确！";
        }else {
            userDO = new UserDO();
            userDO.setUsername(username);
            userDO.setPassword(EncryptUtil.encrypt(password, username));
            userDO.setGmtCreate(Timestamp.valueOf(LocalDateTime.now()));
            userDO.setGmtModified(Timestamp.valueOf(LocalDateTime.now()));
            userDO.setForbidden((byte) 0);
            userDO.setNickname("test");
            userDAO.save(userDO);
            code = 0;
            msg = "succeed";
        }
        return new ResponseDTO(code, msg, userDO);
    }
}
