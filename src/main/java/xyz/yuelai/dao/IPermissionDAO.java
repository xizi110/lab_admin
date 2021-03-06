package xyz.yuelai.dao;

import xyz.yuelai.pojo.domain.PermissionDO;

import java.util.List;

/**
 * @author 李泽众
 * @date 2019/7/23-10:21
 */


public interface IPermissionDAO extends ICommonDAO<PermissionDO> {

    /**
     * 列出所有权限
     * @return
     */
    List<PermissionDO> list();

}
