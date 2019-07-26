package xyz.yuelai.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author 李泽众
 * @date 2019/7/26-10:29
 */


public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    public String getToken() {
        return token;
    }
}
