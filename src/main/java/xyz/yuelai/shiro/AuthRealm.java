package xyz.yuelai.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.yuelai.dao.IUserDAO;
import xyz.yuelai.pojo.domain.UserDO;

/**
 * @author 李泽众
 * @date 2019/7/13-12:42
 */


public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private IUserDAO userDAO;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UserDO userDO = userDAO.getUserByUsername(token.getUsername());
        if (userDO == null) {
            return null;
        } else{
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userDO, userDO.getPassword(), getName());
            return info;
        }
    }
}
