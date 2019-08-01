package xyz.yuelai.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import xyz.yuelai.dao.IEventDAO;
import xyz.yuelai.pojo.domain.EventDO;
import xyz.yuelai.pojo.dto.in.EventFormDTO;

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
        hibernateTemplate.save(eventDO);
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

    /**
     * 决定由前台分页，后台不再分页
     * 分页
     * int index = 0;
     * int offset = Constant.PAGE_COUNT;
     * Integer page = formDTO.getPage();
     * 起始下标不能小于0
     * if( page != null && page > 0){
     *   index = page * Constant.PAGE_COUNT;
     * }
     *
     * 结束
     * if(offset > eventDOList.size()){
     *   offset = eventDOList.size();
     * }
     */
    @Override
    public List<EventDO> list(EventFormDTO formDTO) {

        DetachedCriteria criteria = DetachedCriteria.forClass(EventDO.class);
        if(!StringUtils.isEmpty(formDTO.getTitle())){
            criteria.add(Restrictions.like("title", formDTO.getTitle(), MatchMode.ANYWHERE));
        }
        if(!StringUtils.isEmpty(formDTO.getAuthor())){
            criteria.add(Restrictions.eq("author", formDTO.getAuthor()));
        }
        return (List<EventDO>) hibernateTemplate.findByCriteria(criteria);
    }
}
