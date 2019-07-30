package xyz.yuelai.dao.impl;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import xyz.yuelai.dao.IEventDAO;
import xyz.yuelai.pojo.bo.PageBO;
import xyz.yuelai.pojo.domain.EventDO;
import xyz.yuelai.util.Constant;

import java.math.BigInteger;
import java.util.List;

/**
 * @author 李泽众
 * @date 2019/7/29-14:11
 */

@Repository
public class EventDAOImpl implements IEventDAO {

    private HibernateTemplate hibernateTemplate;

    public EventDAOImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void save(EventDO eventDO) {

    }

    @Override
    public void deleteById(EventDO eventDO) {

    }

    @Override
    public void update(EventDO eventDO) {

    }

    @Override
    public EventDO getById(Long id) {
        return null;
    }

    @Override
    public PageBO<EventDO> list(int page) {
        return hibernateTemplate.execute(session -> {
            long totalCount = (long)session.createQuery("select count(1) from EventDO").uniqueResult();
            Query query = session.createQuery("from EventDO");
            query.setFirstResult(page * Constant.PAGE_COUNT);
            query.setMaxResults(Constant.PAGE_COUNT);
            List<EventDO> eventList = query.list();
            return new PageBO<>(page, totalCount, eventList);
        });
    }
}
