package xyz.yuelai.service.impl;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.yuelai.dao.IUserDAO;
import xyz.yuelai.pojo.domain.UserDO;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.service.IAuthService;
import xyz.yuelai.util.Constant;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author 李泽众
 * @date 2019/7/12-12:38
 */

@Service
public class AuthServiceImpl implements IAuthService {

    private IUserDAO userDAO;
    private HibernateTemplate hibernateTemplate;

    public AuthServiceImpl(IUserDAO userDAO, HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
        this.userDAO = userDAO;
    }

    @Override
    public ResponseDTO login(String username, String password) {

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

        }else {
            code = 0;
            msg = "succeed";
            userDO = new UserDO();
            userDO.setUsername(username);
            userDO.setPassword(password);
        }

        return new ResponseDTO(code, msg, userDO);
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

        }else if(StringUtils.isEmpty(vcode) || vcode.length() > Constant.LEGAL_STRING_LENGTH){
            code = -1;
            msg = "验证码不正确！";
        }else {
            userDO = new UserDO();
            userDO.setUsername(username);
            userDO.setPassword(password);
            userDO.setGmtCreate(Timestamp.valueOf(LocalDateTime.now()));
            userDO.setGmtModified(Timestamp.valueOf(LocalDateTime.now()));
            userDO.setForbidden((byte) 0);
            userDO.setNickname("test");
            hibernateTemplate.save(userDO);
            code = 0;
            msg = "succeed";
        }
        return new ResponseDTO(code, msg, userDO);
    }
}
