package xyz.yuelai.dao;

import xyz.yuelai.pojo.bo.PageBO;
import xyz.yuelai.pojo.domain.EventDO;

/**
 * @author 李泽众
 * @date 2019/7/29-14:11
 */


public interface IEventDAO extends ICommonDAO<EventDO> {

    /**
     * 查询事记
     * @param page  页码
     * @return
     */
    PageBO<EventDO> list(int page);

}
