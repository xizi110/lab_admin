package xyz.yuelai.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import xyz.yuelai.util.EncryptUtil;

/**
 * @author 李泽众
 * @date 2019/7/13-12:45
 */


public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        String tokenCredentials = EncryptUtil.encrypt(String.valueOf(userToken.getPassword()), userToken.getUsername());

        Object accountCredentials = getCredentials(info);

        return equals(tokenCredentials, accountCredentials);
    }
}
