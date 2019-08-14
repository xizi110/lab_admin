package xyz.yuelai.dao;

import xyz.yuelai.pojo.bo.PageBO;
import xyz.yuelai.pojo.domain.EventDO;
import xyz.yuelai.pojo.dto.in.EventFormDTO;

/**
 * @author 李泽众
 * @date 2019/7/29-14:11
 */


public interface IEventDAO extends ICommonDAO<EventDO> {

    /**
     * 查询事记，根据查询条件查询，还可进行分页
     * @param formDTO  封装的前台查询条件
     * @return
     */
    PageBO<EventDO> list(EventFormDTO formDTO);

}
