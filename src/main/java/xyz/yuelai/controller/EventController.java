package xyz.yuelai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.yuelai.pojo.domain.EventDO;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.service.IEventService;
import xyz.yuelai.util.Constant;

/**
 * @author 李泽众
 * @date 2019/7/24-20:33
 */

@Api(tags = "用于处理大事计")
@Controller
@RequestMapping(value = "/event")
public class EventController {

    private IEventService eventService;

    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    /**
     * 大事记页面初始化
     * @return  大事记列表
     */
    @ApiOperation(value = "获取大事记列表")
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseDTO list(){
        int currentPage  = 1;
        return eventService.list(currentPage - 1);
    }

    /**
     * 添加大事记，大事记写作业面
     * @return
     */
    @ApiOperation(value = "添加大事记")
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseDTO add(@RequestBody EventDO eventDO){
        return eventService.save(eventDO);
    }

    /**
     *  权限测试
     * */
    @ApiOperation(value = "replay")
    @ResponseBody
    @RequestMapping(value = "/replay", method = RequestMethod.POST)
    public ResponseDTO delete1(){
        return new ResponseDTO(Constant.CODE_OK, "还有/replay！");
    }

}
