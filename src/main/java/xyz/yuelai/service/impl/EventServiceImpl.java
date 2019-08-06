package xyz.yuelai.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.yuelai.dao.IEventDAO;
import xyz.yuelai.pojo.domain.EventDO;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.pojo.dto.in.EventFormDTO;
import xyz.yuelai.service.IEventService;
import xyz.yuelai.util.Constant;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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
    public ResponseDTO list(EventFormDTO formDTO) {
        if(formDTO.getPage() < 0){
            formDTO.setPage(0);
        }else {
            formDTO.setPage(formDTO.getPage() - 1);
        }

        List<EventDO> eventList = eventDAO.list(formDTO);
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

    @Override
    public ResponseDTO delete(long eventId) {
        Integer code;
        String msg;
        EventDO eventDO = eventDAO.getById(eventId);
        if (eventDO == null) {
            code = Constant.CODE_NOT_OK;
            msg = String.format("删除失败，不存在事记id为[%d]的事记！", eventId);
        } else {
            eventDAO.deleteById(eventDO);
            code = Constant.CODE_OK;
            msg = "删除成功";
        }
        return new ResponseDTO(code, msg);
    }
}
