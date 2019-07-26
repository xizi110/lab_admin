package xyz.yuelai.shiro;

import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.yuelai.dao.IPermissionDAO;
import xyz.yuelai.dao.IUserDAO;
import xyz.yuelai.pojo.domain.PermissionDO;
import xyz.yuelai.service.IPermissionService;
import xyz.yuelai.service.IUserService;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 李泽众
 * @date 2019/7/24-17:30
 *
 * 此类主要配置拦截器链
 */

public class CustomShiroFilterFactoryBean extends ShiroFilterFactoryBean {

    @Autowired
    private IPermissionService permissionService;

    @Override
    public void setFilterChainDefinitions(String definitions) {
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }
        List<PermissionDO> permissionDOList = permissionService.list();
        /* 从数据库配置权限 */
        for (PermissionDO permissionDO : permissionDOList) {
            String permissionURI = permissionDO.getPermissionURI();
            String permissionValue = permissionDO.getPermissionValue();
            section.put(permissionURI, "perms[" + permissionValue + "]");
        }
        section.put("/swagger-ui.html","anon");
        section.put("/swagger-resources/*","anon");
        section.put("/webjars/*","anon");
        section.put("/auth/login", "anon");
        section.put("/auth/401", "anon");
        section.put("/auth/logout", "authc");
        setFilterChainDefinitionMap(section);
    }
}
