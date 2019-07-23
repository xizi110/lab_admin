package xyz.yuelai.dao;

import xyz.yuelai.pojo.domain.RoleDO;

import java.util.List;

/**
 * @author 李泽众
 * @date 2019/7/15-13:00
 */


public interface IRoleDAO extends ICommonDAO<RoleDO> {

    List<RoleDO> listByUserId(Long userId);
}
