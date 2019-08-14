package xyz.yuelai.filter;

import lombok.extern.log4j.Log4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import xyz.yuelai.shiro.JwtToken;
import xyz.yuelai.util.Constant;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 李泽众
 * @date 2019/7/26-10:27
 */

@Log4j
public class AuthShiroFilter extends BasicHttpAuthenticationFilter {


    /**
     * 检测Header里面是否包含Authorization请求头
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        return getAuthzHeader(request) != null;
    }

    /**
     * 调用AuthRealm认证
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        JwtToken token = new JwtToken(getAuthzHeader(request));
        getSubject(request, response).login(token);
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String requestURI = req.getRequestURI();

        /* 放开swagger静态资源和跨域OPTIONS请求 */
        if(requestURI.startsWith("/swagger") || requestURI.startsWith("/webjars") ||
                requestURI.startsWith("/v2") || "OPTIONS".equals(req.getMethod())){
            return true;
        }

        /* 放开登录请求 */
        String loginUri = "/auth/login";
        if (loginUri.equals(requestURI)) {
            return true;
        }
        /* 请求头含有Authorization字段 */
        if (isLoginAttempt(request, response)) {
            try {
                /* 调用AuthRealm的doGetAuthenticationInfo方法，验证token */
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                log.error(e.getMessage());
                /* token失效 */
                response(response, Constant.CODE_INVALID_TOKEN,"令牌已过期，请重新登录！");
            }
        }else {
            /* 未登录 */
            response(response, Constant.CODE_UNAUTHENTICATED,"请登录！");
        }
        return false;
    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
           /*  不再响应401，由controller返回响应信息 */
//        sendChallenge(request, response);
        return false;
    }

    /**
     * @param resp
     * @param code 返回码
     * @param msg 返回信息
     */
    private void response(ServletResponse resp, int code, String msg) {
        HttpServletResponse response = (HttpServletResponse) resp;
        PrintWriter writer = null;
        try {
            response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            writer = response.getWriter();
            writer.write("{\"code\": " + code + ",\"msg\": \"" + msg + "\"}");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null){
                writer.close();
            }
        }
    }
}
