package xyz.yuelai.dao.impl;

import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import xyz.yuelai.dao.IPermissionDAO;
import xyz.yuelai.pojo.domain.PermissionDO;

import java.util.List;

/**
 * @author 李泽众
 * @date 2019/7/23-10:23
 */

@Repository
public class PermissionDAOImpl implements IPermissionDAO {

    private HibernateTemplate hibernateTemplate;

    public PermissionDAOImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    private Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }


    @Override
    public List<PermissionDO> list() {

        List<PermissionDO> permissions = hibernateTemplate.execute(session -> {
            String sql = "SELECT * FROM auth_permission";
            return session.createSQLQuery(sql).addEntity(PermissionDO.class).list();
        });
        return permissions;
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

}
