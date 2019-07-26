package xyz.yuelai.shiro;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author 李泽众
 * @date 2019/7/25-11:20
 */


public class SessionManager extends DefaultWebSessionManager {

//    @Override
//    public SessionDAO () {
//        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
//        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
//        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
//        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
//        return subjectDAO;
//    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        return getAuthorizationHead(request);
    }

    @Override
    public Cookie getSessionIdCookie() {
        return new SimpleCookie();
    }


    @Override
    public boolean isSessionIdCookieEnabled() {
        return false;
    }

    private Serializable getAuthorizationHead(ServletRequest request) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String authorization = httpServletRequest.getHeader("Authorization");

        if (authorization != null) {
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, authorization);
            //automatically mark it valid here.  If it is invalid, the
            //onUnknownSession method below will be invoked and we'll remove the attribute at that time.
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
        }
        return authorization;

    }
}
