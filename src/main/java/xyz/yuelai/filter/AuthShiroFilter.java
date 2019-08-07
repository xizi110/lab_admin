package xyz.yuelai.filter;

import lombok.extern.log4j.Log4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import xyz.yuelai.shiro.JwtToken;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        /* 放开swagger静态资源 */
        if(requestURI.startsWith("/swagger") || requestURI.startsWith("/webjars") ||
                requestURI.startsWith("/v2")){
            return true;
        }

        String loginUri = "/auth/login";
        if (loginUri.equals(requestURI)) {
            return true;
        }
        /* 请求头含有Authorization字段 */
        if (isLoginAttempt(request, response)) {
            try {
                /* 调用AuthRealm的doGetAuthenticationInfo方法，验证token */
                return executeLogin(request, response);
            } catch (Exception e) {
                log.error(e.getMessage());
                /* token失效 */
                response(request,response, "/auth/401");
            }
        }else {
            /* 未登录 */
            response(request,response, "/auth/unauthenticated");
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
     * @param req
     * @param resp
     * @param path 服务器内部跳转controller，进行响应
     */
    private void response(ServletRequest req, ServletResponse resp, String path) {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;

        try {
            request.getRequestDispatcher(path).forward(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
