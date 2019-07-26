package xyz.yuelai.shiro;

import lombok.extern.log4j.Log4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import xyz.yuelai.pojo.domain.PermissionDO;
import xyz.yuelai.pojo.domain.RoleDO;
import xyz.yuelai.pojo.domain.UserDO;
import xyz.yuelai.service.IUserService;
import xyz.yuelai.util.Constant;
import xyz.yuelai.util.JwtUtil;

import java.util.List;

/**
 * @author 李泽众
 * @date 2019/7/13-12:42
 */

@Log4j
@Component
public class AuthRealm extends AuthorizingRealm {

    private IUserService userService;

    public AuthRealm(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        UserDO user = (UserDO) principalCollection.getPrimaryPrincipal();

        String username = JwtUtil.getClaim(principalCollection.toString(), Constant.USERNAME);
        log.info(username);

        List<RoleDO> roleDOList = userService.listRolesByUserId(user.getUserId());
        roleDOList.forEach(action -> info.addRole(action.getRoleName()));


        for (RoleDO roleDO : roleDOList) {
            info.addRole(roleDO.getRoleName());
            List<PermissionDO> permissionDOList = userService.listPermissionsByRoleId(roleDO.getRoleId());
            permissionDOList.forEach(action -> info.addStringPermission(action.getPermissionValue()));
        }

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken token = (JwtToken) authenticationToken;

        if (token == null) {
            throw new AuthenticationException("Token Invalid! Token is null!");
        }

        String username = JwtUtil.getClaim(token.getToken(), Constant.USERNAME);

        if (username == null) {
            throw new AuthenticationException("Token Invalid! username is null");
        }

        UserDO userDO = (UserDO) userService.getUserByUsername(username).getData();

        if (userDO == null) {
            return null;
        }

        if (JwtUtil.verify(token.getToken())) {
            return new SimpleAuthenticationInfo(token.getToken(), token.getToken(), getName());
        }
        throw new AuthenticationException("Token Invalid! UnKnow case!");
    }
}
