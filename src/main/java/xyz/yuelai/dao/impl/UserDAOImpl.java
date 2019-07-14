package xyz.yuelai.dao.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import xyz.yuelai.dao.IUserDAO;
import xyz.yuelai.pojo.domain.UserDO;

import java.util.List;

/**
 * @author 李泽众
 * @date 2019/7/11-20:55
 */

@Repository
public class UserDAOImpl implements IUserDAO {

    private HibernateTemplate hibernateTemplate;

    public UserDAOImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void save(UserDO userDO) {
        hibernateTemplate.save(userDO);
    }

    @Override
    public void deleteById(UserDO userDO) {
        hibernateTemplate.delete(userDO);
    }

    @Override
    public void update(UserDO userDO) {
        hibernateTemplate.update(userDO);
    }

    @Override
    public UserDO getById(Long id) {
        return hibernateTemplate.get(UserDO.class, id);
    }

    @Override
    public List<UserDO> listBySQL(String sql) {
        List<UserDO> userDOList = getSession().createSQLQuery(sql).addEntity(UserDO.class).list();
        return userDOList;
    }

    private Session getSession(){
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    @Override
    public UserDO getUserByUsername(String username) {
        String hql = "from UserDO u where u.username = :username";
        UserDO userDO = getSession().createQuery(hql, UserDO.class)
                .setParameter("username", username)
                .getSingleResult();
        return userDO;
    }
}
