package xyz.yuelai.dao.impl;

import org.hibernate.LockMode;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import xyz.yuelai.dao.IEventDAO;
import xyz.yuelai.pojo.domain.EventDO;
import xyz.yuelai.pojo.dto.in.EventFormDTO;
import xyz.yuelai.util.Constant;

import java.util.ArrayList;
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
        hibernateTemplate.delete(eventDO);
    }

    @Override
    public void update(EventDO eventDO) {
        hibernateTemplate.update(eventDO, LockMode.READ);
    }

    @Override
    public EventDO getById(Long id) {
        return hibernateTemplate.get(EventDO.class, id);
    }

    @Override
    public List<EventDO> list(EventFormDTO formDTO) {

        DetachedCriteria criteria = DetachedCriteria.forClass(EventDO.class);

        if(!StringUtils.isEmpty(formDTO.getTitle())){
            criteria.add(Restrictions.like("title", formDTO.getTitle(), MatchMode.ANYWHERE));
        }
        if(!StringUtils.isEmpty(formDTO.getAuthor())){
            criteria.add(Restrictions.eq("author", formDTO.getAuthor()));
        }

        /* 不查询内容列 */
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("eventId").as("eventId"));
        projectionList.add(Projections.property("title").as("title"));
        projectionList.add(Projections.property("author").as("author"));
        projectionList.add(Projections.property("brief").as("brief"));
        projectionList.add(Projections.property("publishDate").as("publishDate"));
        projectionList.add(Projections.property("carousel").as("carousel"));
        projectionList.add(Projections.property("carouselImgLink").as("carouselImgLink"));
        projectionList.add(Projections.property("carouselEndDate").as("carouselEndDate"));
        criteria.setProjection(projectionList).setResultTransformer(Transformers.aliasToBean(EventDO.class));

        List<EventDO> eventDOList = (List<EventDO>) hibernateTemplate.findByCriteria(criteria);

        int fromIndex = formDTO.getPage() * Constant.PAGE_COUNT;
        int toIndex = fromIndex + Constant.PAGE_COUNT;

        if(toIndex > eventDOList.size()){
            toIndex = eventDOList.size();
        }

        List<EventDO> eventDOS = new ArrayList<>();
        /* 起始下标大于0小于max，终止下标大于等于起始下标，小于等于max */
        if(fromIndex < eventDOList.size() && toIndex >= fromIndex){
            eventDOS = eventDOList.subList(fromIndex, toIndex);
        }
        return eventDOS;
    }
}
