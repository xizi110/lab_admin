package xyz.yuelai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李泽众
 * @date 2019/7/11-10:27
 * 此类处理有关用户登录，注册等的请求
 */

@Api(tags = "用于处理用户登录、注册、登出请求")
@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    @ApiOperation(value = "用于处理用户的登陆请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户登陆名", required = true),
            @ApiImplicitParam(name = "password", value = "用户登陆密码", required = true)})
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, String> login(@RequestParam String username, @RequestParam String password) {
        Map<String, String> params = new HashMap<String, String>(5){{
            put("username", username);
            put("password", password);
        }};
        System.out.println();
        System.out.println(params);
        return params;
    }

    @ApiOperation(value = "用于处理用户的注册请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户注册的账户名", required = true),
            @ApiImplicitParam(name = "password", value = "用户注册的密码", required = true),
            @ApiImplicitParam(name = "code", value = "验证码", required = true)})
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, String> register(@RequestParam String username, @RequestParam String password, @RequestParam String code) {

        Map<String, String> params = new HashMap<String, String>(5){{
            put("username", username);
            put("password", password);
            put("code", code);
        }};
        System.out.println(params);
        return params;
    }

    @ApiOperation(value = "用于处理用户的登出请求")
    @ApiImplicitParams({ @ApiImplicitParam(name = "username", value = "登录的用户名", required = true) })
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Map<String, String> logout(@RequestParam String username) {

        Map<String, String> params = new HashMap<String, String>(5){{
            put("username", username);
            put("msg", "logout登出");
        }};
        System.out.println(params);
        return params;
    }



}
