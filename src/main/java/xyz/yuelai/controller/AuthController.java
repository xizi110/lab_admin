package xyz.yuelai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.pojo.dto.in.LoginDTO;
import xyz.yuelai.service.IAuthService;
import xyz.yuelai.util.Constant;

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

    private IAuthService authService;


    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @ApiOperation(value = "用于处理用户的登陆请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户登陆名", required = true),
            @ApiImplicitParam(name = "password", value = "用户登陆密码", required = true)})
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseDTO login(@RequestBody LoginDTO loginDTO) {
        ResponseDTO responseDTO = authService.login(loginDTO.getUsername(), loginDTO.getPassword());
        return responseDTO;
    }

    @ApiOperation(value = "用于处理用户的注册请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户注册的账户名", required = true),
            @ApiImplicitParam(name = "password", value = "用户注册的密码", required = true),
            @ApiImplicitParam(name = "code", value = "验证码", required = true)})
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseDTO register(@RequestParam String username, @RequestParam String password, @RequestParam String code) {
        ResponseDTO responseDTO = authService.register(username, password, code);
        return responseDTO;
    }

    @ApiOperation(value = "用于处理用户的登出请求")
    @ApiImplicitParams({ @ApiImplicitParam(name = "username", value = "登录的用户名", required = true) })
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseDTO logout(@RequestParam String username) {
        /*
         *
         *
         *
         */
        return new ResponseDTO(Constant.CODE_OK, "登出成功");
    }

    @ApiOperation(value = "服务器返回未认证信息")
    @ResponseBody
    @RequestMapping(value = "/unauthenticated", method = RequestMethod.GET)
    public ResponseDTO unauthenticated() {
        return new ResponseDTO(Constant.CODE_UNAUTHENTICATED, "未认证用户。");
    }





}
