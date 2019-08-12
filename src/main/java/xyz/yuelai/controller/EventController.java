package xyz.yuelai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import xyz.yuelai.pojo.domain.EventDO;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.pojo.dto.in.EventFormDTO;
import xyz.yuelai.service.IEventService;
import xyz.yuelai.util.Constant;

/**
 * @author 李泽众
 * @date 2019/7/24-20:33
 */

@Api(tags = "用于处理大事记")
@RestController
public class EventController {

    private IEventService eventService;

    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    /**
     * 单个事记查询
     * event:get权限
     * @return  一条被查询到的大事记
     */
    @ApiOperation(value = "查询单个大事记")
    @ResponseBody
    @RequestMapping(value = "/event/{eventId}", method = RequestMethod.GET)
    @RequiresPermissions("event:get")
    public ResponseDTO get(@PathVariable Long eventId) {
        if (StringUtils.isEmpty(eventId)) {
            return new ResponseDTO(Constant.CODE_ERROR_PARAMS, "请求参数出错！");
        }
        return eventService.get(eventId);
    }

    /**
     * 大事记查询，分页
     * event:list权限
     * @return  大事记列表
     */
    @ApiOperation(value = "获取大事记列表")
    @ResponseBody
    @RequestMapping(value = "/event", method = RequestMethod.GET)
    @RequiresPermissions("event:list")
    public ResponseDTO list(EventFormDTO formDTO) {
        if (formDTO == null) {
            return new ResponseDTO(Constant.CODE_ERROR_PARAMS, "请求参数出错！");
        }
        return eventService.list(formDTO);
    }

    /**
     * 添加大事记，大事记写作业面
     * event:add权限
     * @return
     */
    @ApiOperation(value = "添加大事记")
    @ResponseBody
    @RequestMapping(value = "/event", method = RequestMethod.POST)
    @RequiresPermissions("event:add")
    public ResponseDTO add(@RequestBody EventDO eventDO){
        if (eventDO == null) {
            return new ResponseDTO(Constant.CODE_ERROR_PARAMS, "请求参数出错！");
        }
        return eventService.save(eventDO);
    }

    /**
     * 删除事记，根据id删除
     * event:delete权限
     * @param eventId   事记id
     * @return
     */
    @ApiOperation(value = "删除大事记")
    @ResponseBody
    @RequestMapping(value = "/event", method = RequestMethod.DELETE)
    @RequiresPermissions("event:delete")
    public ResponseDTO delete(Long eventId){
        if (StringUtils.isEmpty(eventId)) {
            return new ResponseDTO(Constant.CODE_ERROR_PARAMS, "请求参数出错！");
        }
        return eventService.delete(eventId);
    }

    @ApiOperation(value = "更新大事记")
    @ResponseBody
    @RequestMapping(value = "/event", method = RequestMethod.PUT)
    @RequiresPermissions("event:update")
    public ResponseDTO update(@RequestBody EventDO eventDO){
        if (eventDO == null) {
            return new ResponseDTO(Constant.CODE_ERROR_PARAMS, "请求参数出错！");
        }
        return eventService.update(eventDO);
    }
}
