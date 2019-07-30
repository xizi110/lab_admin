package xyz.yuelai.service;

import xyz.yuelai.pojo.dto.ResponseDTO;

/**
 * @author 李泽众
 * @date 2019/7/24-21:44
 */


public interface IEventService {

    /**
     * 查询事记
     * @param currentPage  页码
     * @return
     */
    ResponseDTO list(int currentPage);

}
