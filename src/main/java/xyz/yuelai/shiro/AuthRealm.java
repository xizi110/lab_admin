package xyz.yuelai.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.yuelai.dao.IPermissionDAO;
import xyz.yuelai.dao.IRoleDAO;
import xyz.yuelai.dao.IUserDAO;
import xyz.yuelai.pojo.domain.PermissionDO;
import xyz.yuelai.pojo.domain.RoleDO;
import xyz.yuelai.pojo.domain.UserDO;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author 李泽众
 * @date 2019/7/13-12:42
 */


public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private IRoleDAO roleDAO;
    @Autowired
    private IPermissionDAO permissionDAO;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserDO user = (UserDO) principalCollection.getPrimaryPrincipal();
        List<RoleDO> roleDOList = roleDAO.listByUserId(user.getUserId());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<PermissionDO> permissionDOList = permissionDAO.listByUserId(user.getUserId());
        roleDOList.forEach(action -> info.addRole(action.getRoleName()));
        permissionDOList.forEach(action -> info.addStringPermission(action.getPermissionURI()));
        return info;
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
