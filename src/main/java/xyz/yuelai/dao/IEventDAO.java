package xyz.yuelai.dao;

import xyz.yuelai.pojo.domain.EventDO;
import xyz.yuelai.pojo.dto.in.EventFormDTO;

import java.util.List;

/**
 * @author 李泽众
 * @date 2019/7/29-14:11
 */


public interface IEventDAO extends ICommonDAO<EventDO> {

    /**
     * 查询事记
     * @param formDTO  查询条件
     * @return
     */
    List<EventDO> list(EventFormDTO formDTO);

}
