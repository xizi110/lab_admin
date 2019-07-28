package xyz.yuelai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.pojo.dto.in.AuthFormDTO;
import xyz.yuelai.service.IAuthService;
import xyz.yuelai.service.IPermissionService;
import xyz.yuelai.util.Constant;

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
    public ResponseDTO login(@RequestBody AuthFormDTO loginDTO) {

        /* 不要重复登录 */
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return new ResponseDTO(Constant.CODE_AUTHENTICATED, "您已经登录了！");
        }

        return authService.login(loginDTO.getUsername(), loginDTO.getPassword());
    }

    @ApiOperation(value = "用于处理用户的注册请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户注册的账户名", required = true),
            @ApiImplicitParam(name = "password", value = "用户注册的密码", required = true),
            @ApiImplicitParam(name = "code", value = "验证码", required = true)})
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseDTO register(@RequestBody AuthFormDTO registerDTO) {
        return authService.register(registerDTO.getUsername(), registerDTO.getPassword(), registerDTO.getCode());
    }

    @ApiOperation(value = "用于处理用户的登出请求")
    @ApiImplicitParams({@ApiImplicitParam(name = "username", value = "登录的用户名", required = true)})
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseDTO logout(@RequestParam String username) {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            SecurityUtils.getSubject().logout();
            return new ResponseDTO(Constant.CODE_OK, "登出成功！");
        } else {
            return new ResponseDTO(Constant.CODE_OK, "您已经退出登录了！");
        }
    }

    @ApiOperation(value = "服务器返回未认证信息")
    @ResponseBody
    @RequestMapping(value = "/unauthenticated", method = RequestMethod.GET)
    public ResponseDTO unauthenticated() {
        return new ResponseDTO(Constant.CODE_UNAUTHENTICATED, "未认证用户。");
    }

    @ApiOperation(value = "服务器返回未授权信息")
    @ResponseBody
    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public ResponseDTO unauthorized() {
        return new ResponseDTO(Constant.CODE_UNAUTHORIZED, "用户没有权限！");
    }

    @ResponseBody
    @RequestMapping(value = "/401")
    public ResponseDTO httpCode401() {
        return new ResponseDTO(Constant.CODE_INVALID_TOKEN, "无效的token");
    }


}
