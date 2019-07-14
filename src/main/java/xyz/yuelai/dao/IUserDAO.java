package xyz.yuelai.dao;

import xyz.yuelai.pojo.domain.UserDO;

/**
 * @author 李泽众
 * @date 2019/7/11-20:51
 *
 * user dao操作
 */

public interface IUserDAO extends ICommonDAO<UserDO> {

    UserDO getUserByUsername(String username);

}
