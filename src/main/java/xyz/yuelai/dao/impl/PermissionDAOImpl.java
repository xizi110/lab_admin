package xyz.yuelai.dao.impl;

import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import xyz.yuelai.dao.IPermissionDAO;
import xyz.yuelai.pojo.domain.PermissionDO;

import java.util.List;

/**
 * @author 李泽众
 * @date 2019/7/23-10:23
 */


@Service
public class PermissionDAOImpl implements IPermissionDAO {

    private HibernateTemplate hibernateTemplate;

    public PermissionDAOImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    private Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }


    @Override
    public List<PermissionDO> listByUserId(Long userId) {
        String sql = "select auth_permission.* from auth_permission,auth_user_permission " +
                "where auth_user_permission.user_id = :user_id and  " +
                "auth_permission.permission_id = auth_user_permission.permission_id";

        List<PermissionDO> permissionDOList = getSession().createSQLQuery(sql)
                .setParameter("user_id", userId)
                .addEntity(PermissionDO.class).list();
        return permissionDOList;
    }

    @Override
    public void save(PermissionDO permissionDO) {

    }

    @Override
    public void deleteById(PermissionDO permissionDO) {

    }

    @Override
    public void update(PermissionDO permissionDO) {

    }

    @Override
    public PermissionDO getById(Long id) {
        return null;
    }

    @Override
    public List<PermissionDO> listBySQL(String sql) {

        return null;
    }
}
