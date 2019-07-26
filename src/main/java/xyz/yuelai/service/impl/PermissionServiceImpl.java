package xyz.yuelai.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.yuelai.dao.IPermissionDAO;
import xyz.yuelai.pojo.domain.PermissionDO;
import xyz.yuelai.service.IPermissionService;

import java.util.List;

/**
 * @author 李泽众
 * @date 2019/7/24-21:44
 */

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDAO permissionDAO;

    @Override
    public List<PermissionDO> list() {
        return permissionDAO.list();
    }
}
