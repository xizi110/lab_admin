package xyz.yuelai.dao;

import xyz.yuelai.pojo.domain.PermissionDO;
import xyz.yuelai.pojo.domain.RoleDO;
import xyz.yuelai.pojo.domain.UserDO;

import java.util.List;

/**
 * @author 李泽众
 * @date 2019/7/11-20:51
 * <p>
 * user dao操作
 */

public interface IUserDAO extends ICommonDAO<UserDO> {

    /**
     * 根据用户名获取用户
     *
     * @param username  用户名
     * @return
     */
    UserDO getUserByUsername(String username);

    /**
     * 根据用户id获取用户角色
     *
     * @param userId    用户id
     * @return
     */
    List<RoleDO> listRolesByUserId(Long userId);

    /**
     * 根据角色id获取角色权限
     *
     * @param roleId    角色id
     * @return
     */
    List<PermissionDO> listPermissionsByRoleId(Long roleId);

}
