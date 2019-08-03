package xyz.yuelai.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.yuelai.pojo.domain.PermissionDO;
import xyz.yuelai.service.IPermissionService;

import java.util.List;
import java.util.Map;

/**
 * @author 李泽众
 * @date 2019/7/24-17:30
 *
 * 此类主要配置拦截器链
 */

public class CustomShiroFilterFactoryBean extends ShiroFilterFactoryBean {

    @Autowired
    private IPermissionService permissionService;

    /**
     * 覆盖get方法，因为filter执行set时，service还未注入
     * @return
     */
    @Override
    public Map<String, String> getFilterChainDefinitionMap() {
        Map<String, String> filterChainDefinitionMap = super.getFilterChainDefinitionMap();
        List<PermissionDO> permissionDOList = permissionService.list();
        /* 不经过jwt认证 */
        filterChainDefinitionMap.put("/auth/unauthenticated", "anon");
        filterChainDefinitionMap.put("/auth/unauthorized", "anon");
        filterChainDefinitionMap.put("/auth/401", "anon");
        filterChainDefinitionMap.put("/auth/logout", "authc");

        /* 其余链接都要经过jwt认证 */
        filterChainDefinitionMap.put("/**", "jwt");

        /* 从数据库配置权限,个人权限认证 */
        for (PermissionDO permissionDO : permissionDOList) {
            String permissionURI = permissionDO.getPermissionURI();
            String permissionValue = permissionDO.getPermissionValue();
            filterChainDefinitionMap.put(permissionURI, "perms[" + permissionValue + "]");
        }

//        setFilterChainDefinitionMap(filterChainDefinitionMap);
        return filterChainDefinitionMap;
    }

    @Override
    public void setFilterChainDefinitions(String definitions) {

        super.setFilterChainDefinitions(definitions);
    }
}
