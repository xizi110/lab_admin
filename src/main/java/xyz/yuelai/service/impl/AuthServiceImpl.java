package xyz.yuelai.service.impl;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.yuelai.dao.IUserDAO;
import xyz.yuelai.pojo.domain.UserDO;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.service.IAuthService;
import xyz.yuelai.shiro.JwtToken;
import xyz.yuelai.util.Constant;
import xyz.yuelai.util.EncryptUtil;
import xyz.yuelai.util.JwtUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 用户登录业务
     *
     * @param username 用户名
     * @param password 密码
     * @return  响应客户端的信息
     */
    @Override
    public ResponseDTO login(String username, String password) {

        int code;
        String msg;
        Map<String, String> tokenStr = new HashMap<>(1);

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            code = Constant.CODE_ERROR_PARAMS;
            msg = "用户名或密码非法！";
        } else if (username.length() > Constant.LEGAL_STRING_LENGTH ||
                password.length() > Constant.LEGAL_STRING_LENGTH) {
            code = Constant.CODE_ERROR_PARAMS;
            msg = String.format("用户名或密码长度过长，请保证在%d个字符以内！", Constant.LEGAL_STRING_LENGTH);

        } else {
            UserDO userDO = userDAO.getUserByUsername(username);
            String loginPassword = EncryptUtil.encrypt(password, username);

            if (!userDO.getPassword().equals(loginPassword)) {
                code = Constant.CODE_UNAUTHENTICATED;
                msg = "认证失败，用户名或密码错误！！";
            } else {
                JwtToken token = new JwtToken(JwtUtil.sign(username, String.valueOf(System.currentTimeMillis())));
                code = Constant.CODE_OK;
                msg = "登录成功！";
                tokenStr.put("token", token.getToken());
            }
        }
        return new ResponseDTO(code, msg, tokenStr);
    }

    /**
     * 用户注册业务
     *
     * @param username 用户名
     * @param password 密码
     * @param vcode    验证码
     * @return
     */
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
            msg = String.format("用户名或密码长度过长，请保证在%d个字符以内！", Constant.LEGAL_STRING_LENGTH);
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
            msg = "注册成功,请登录！";
        }
        userDO.setPassword(null);
        userDO.setGmtCreate(null);
        userDO.setGmtModified(null);
        userDO.setForbidden(null);
        return new ResponseDTO(code, msg, userDO);
    }
}
