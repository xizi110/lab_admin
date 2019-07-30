package xyz.yuelai.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.yuelai.dao.IEventDAO;
import xyz.yuelai.pojo.bo.PageBO;
import xyz.yuelai.pojo.domain.EventDO;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.service.IEventService;
import xyz.yuelai.util.Constant;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author 李泽众
 * @date 2019/7/29-14:06
 */


@Service
public class EventServiceImpl implements IEventService {

    private IEventDAO eventDAO;

    public EventServiceImpl(IEventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    public ResponseDTO list(int currentPage) {
        if (currentPage < 0) {
            currentPage = 0;
        }
        PageBO<EventDO> eventList = eventDAO.list(currentPage);
        return new ResponseDTO(Constant.CODE_OK, "查询成功！", eventList);
    }

    @Override
    public ResponseDTO save(EventDO eventDO) {
        Integer code;
        String msg;
        if (eventDO == null) {
            code = Constant.CODE_ERROR_PARAMS;
            msg = "请求参数出错！！";
        } else {
            String author = eventDO.getAuthor();
            String title = eventDO.getTitle();
            Byte isCarousel = eventDO.getCarousel();

            if (StringUtils.isEmpty(author) || StringUtils.isEmpty(title)) {
                code = Constant.CODE_ERROR_PARAMS;
                msg = "作者和标题均不能为空！";
            } else if (isCarousel == null || isCarousel > 1 || isCarousel < 0) {
                code = Constant.CODE_ERROR_PARAMS;
                msg = "参数[carousel]值异常，可取值[0, 1]！";
            } else {
                eventDO.setPublishDate(Timestamp.valueOf(LocalDateTime.now()));
                eventDAO.save(eventDO);
                code = Constant.CODE_OK;
                msg = "事记保存成功！";
            }
        }

        return new ResponseDTO(code, msg);
    }
}
