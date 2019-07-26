package xyz.yuelai.filter;

import lombok.extern.log4j.Log4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import xyz.yuelai.shiro.JwtToken;
import xyz.yuelai.util.Constant;
import xyz.yuelai.util.JwtUtil;

import javax.servlet.ServletException;
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
        if(isLoginAttempt(request, response)){
            try {
                return executeLogin(request, response);
            } catch (Exception e) {
                e.printStackTrace();
//                response401(request, response);
            }
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        sendChallenge(request, response);
        return false;
    }

    /**
     * 401非法请求
     * @param req
     * @param resp
     */
    private void response401(ServletRequest req, ServletResponse resp) {
        HttpServletRequest request = (HttpServletRequest) req;
        try {
            request.getRequestDispatcher("/auth/401").forward(request, resp);
        }catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
