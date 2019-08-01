package xyz.yuelai.service.impl;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import xyz.yuelai.util.RedisUtil;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 李泽众
 * @date 2019/7/12-12:38
 */
@Log4j
@Service
public class AuthServiceImpl implements IAuthService {

    private IUserDAO userDAO;

    @Autowired
    private RedisUtil redisUtil;

    public AuthServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * 用户登录业务
     *
     * @param username 用户名
     * @param password 密码
     * @return 响应客户端的信息
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
            Long userId = userDO.getUserId();
            if (userDO == null) {
                code = Constant.CODE_UNAUTHENTICATED;
                msg = "认证失败，用户名或密码错误！！";
            } else {
                String loginPassword = EncryptUtil.encrypt(password, username);

                if (!userDO.getPassword().equals(loginPassword)) {
                    code = Constant.CODE_UNAUTHENTICATED;
                    msg = "认证失败，用户名或密码错误！！";
                } else {
                    String token = new JwtToken(JwtUtil.sign(String.valueOf(userId),username, String.valueOf(System.currentTimeMillis()))).getToken();
                    code = Constant.CODE_OK;
                    msg = "登录成功！";
                    tokenStr.put("token", token);
                    redisUtil.set(username, token);
                    redisUtil.expire(username, Constant.REDIS_TOKEN_EXPIRE_TIME, TimeUnit.MINUTES);
                }
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
        Map<String, Object> data = new HashMap<>(4);

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
        } else if (userDAO.getUserByUsername(username) != null) {
            code = Constant.CODE_NOT_OK;
            msg = "用户名已经存在，请尝试更换一个未被使用的！";
        } else {
            UserDO userDO = new UserDO();
            userDO.setUsername(username);
            userDO.setPassword(EncryptUtil.encrypt(password, username));
            userDO.setGmtCreate(Timestamp.valueOf(LocalDateTime.now()));
            userDO.setGmtModified(Timestamp.valueOf(LocalDateTime.now()));
            userDO.setForbidden((byte) 0);
            userDO.setNickname("test");
            userDAO.save(userDO);

            code = Constant.CODE_OK;
            msg = "注册成功,请登录！";
            data.put("userId", userDO.getUserId());
            data.put("username", userDO.getUsername());
            data.put("nickname", userDO.getNickname());
            data.put("forbidden", userDO.getForbidden());
        }

        return new ResponseDTO(code, msg, data);
    }
}
