package xyz.yuelai.service;

import xyz.yuelai.pojo.domain.EventDO;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.pojo.dto.in.EventFormDTO;

/**
 * @author 李泽众
 * @date 2019/7/24-21:44
 */


public interface IEventService {

    /**
     * 查询事记
     * @param formDTO  表单查询的条件
     * @return
     */
    ResponseDTO list(EventFormDTO formDTO);

    /**
     * 保存事记
     * @param eventDO
     * @return
     */
    ResponseDTO save(EventDO eventDO);

    /**
     * 根据事记id删除事记
     * @param eventId
     * @return
     */
    ResponseDTO delete(long eventId);

    /**
     * 跟新事记
     * @param eventDO
     * @return
     */
    ResponseDTO update(EventDO eventDO);

    /**
     * 获取单个事记
     * @param eventId
     * @return
     */
    ResponseDTO get(Long eventId);
}
