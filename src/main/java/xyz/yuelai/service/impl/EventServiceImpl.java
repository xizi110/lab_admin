package xyz.yuelai.service.impl;

import org.springframework.stereotype.Service;
import xyz.yuelai.dao.IEventDAO;
import xyz.yuelai.pojo.bo.PageBO;
import xyz.yuelai.pojo.domain.EventDO;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.service.IEventService;
import xyz.yuelai.util.Constant;

/**
 * @author 李泽众
 * @date 2019/7/29-14:06
 */


@Service
public class EventServiceImpl implements IEventService{

    private IEventDAO eventDAO;

    public EventServiceImpl(IEventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    public ResponseDTO list(int currentPage) {
        if(currentPage < 0){
            currentPage = 0;
        }
        PageBO<EventDO> eventList = eventDAO.list(currentPage);
        return new ResponseDTO(Constant.CODE_OK, "查询成功！", eventList);
    }
}
