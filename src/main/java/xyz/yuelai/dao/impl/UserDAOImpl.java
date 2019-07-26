package xyz.yuelai.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.lang.Nullable;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import xyz.yuelai.dao.IUserDAO;
import xyz.yuelai.pojo.domain.PermissionDO;
import xyz.yuelai.pojo.domain.RoleDO;
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
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(UserDO.class);
        detachedCriteria.add(Restrictions.eq("username", username));
        List<UserDO> userDOList = (List<UserDO>) hibernateTemplate.findByCriteria(detachedCriteria);
        return userDOList!=null ? userDOList.isEmpty() ? null : userDOList.get(0) : null;
    }

    @Override
    public List<RoleDO> listRolesByUserId(Long userId) {
        List<RoleDO> roles = hibernateTemplate.execute(new HibernateCallback<List<RoleDO>>() {
            @Nullable
            @Override
            public List<RoleDO> doInHibernate(Session session) throws HibernateException {
                String sql = "select auth_role.* from auth_role, auth_user_role " +
                        "WHERE auth_user_role.user_id = :userId and auth_user_role.role_id = auth_role.role_id";
                List<RoleDO> roles = session.createSQLQuery(sql)
                        .setParameter("userId", userId).addEntity(RoleDO.class).list();
                return roles;
            }
        });
        return roles;
    }

    @Override
    public List<PermissionDO> listPermissionsByRoleId(Long roleId) {

        List<PermissionDO> permissions = hibernateTemplate.execute(new HibernateCallback<List<PermissionDO>>() {
            @Nullable
            @Override
            public List<PermissionDO> doInHibernate(Session session) throws HibernateException {
                String sql = "SELECT auth_permission.* FROM auth_permission,auth_role_permission WHERE " +
                        "auth_role_permission.role_id = :roleId AND " +
                        "auth_permission.permission_id = auth_role_permission.permission_id";

                List<PermissionDO> permissions = session.createSQLQuery(sql)
                        .setParameter("roleId", roleId).addEntity(PermissionDO.class).list();
                return permissions;
            }
        });
        return  permissions;
    }
}
