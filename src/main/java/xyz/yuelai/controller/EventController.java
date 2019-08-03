package xyz.yuelai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import xyz.yuelai.pojo.domain.EventDO;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.pojo.dto.in.EventFormDTO;
import xyz.yuelai.service.IEventService;

/**
 * @author 李泽众
 * @date 2019/7/24-20:33
 */

@Api(tags = "用于处理大事计")
@RestController
public class EventController {

    private IEventService eventService;

    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    /**
     * 大事记页面初始化
     * event权限
     * @return  大事记列表
     */
    @ApiOperation(value = "获取大事记列表")
    @ResponseBody
    @RequestMapping(value = "/event", method = RequestMethod.GET)
    @RequiresPermissions("event:list")
    public ResponseDTO list(EventFormDTO formDTO){
        return eventService.list(formDTO);
    }

    /**
     * 添加大事记，大事记写作业面
     * @return
     */
    @ApiOperation(value = "添加大事记")
    @ResponseBody
    @RequestMapping(value = "/event", method = RequestMethod.POST)
    @RequiresPermissions("event:add")
    public ResponseDTO add(@RequestBody EventDO eventDO){
        return eventService.save(eventDO);
    }
}
