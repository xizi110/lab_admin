package xyz.yuelai.dao.impl;

import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import xyz.yuelai.dao.IRoleDAO;
import xyz.yuelai.pojo.domain.RoleDO;

import java.util.List;

/**
 * @author 李泽众
 * @date 2019/7/15-13:02
 */


@Repository
public class RoleDAOImpl implements IRoleDAO{

    private HibernateTemplate hibernateTemplate;

    public RoleDAOImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void save(RoleDO roleDO) {

    }

    @Override
    public void deleteById(RoleDO roleDO) {

    }

    @Override
    public void update(RoleDO roleDO) {

    }

    @Override
    public RoleDO getById(Long id) {
        return hibernateTemplate.get(RoleDO.class, id);
    }

    @Override
    public List<RoleDO> listByUserId(Long userId) {
        return null;
    }

    @Override
    public List<RoleDO> listBySQL(String sql) {
        return null;
    }

    private Session getSession(){
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

}
