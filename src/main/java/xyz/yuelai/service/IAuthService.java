package xyz.yuelai.service;

import xyz.yuelai.pojo.dto.ResponseDTO;

/**
 * @author 李泽众
 * @date 2019/7/12-11:46
 */


public interface IAuthService {

    /**
     * 用户登陆service
     * @param username  用户名
     * @param password  密码
     * @return  响应客户端的封装信息
     */
    ResponseDTO login(String username, String password);

    /**
     * 用户注册service
     * @param username  用户名
     * @param password  密码
     * @param code 验证码
     * @return  响应客户端的封装信息
     */
    ResponseDTO register(String username, String password, String code);

}
