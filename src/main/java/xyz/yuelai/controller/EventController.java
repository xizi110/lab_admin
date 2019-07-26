package xyz.yuelai.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.util.Constant;

/**
 * @author 李泽众
 * @date 2019/7/24-20:33
 */

@Api(tags = "用于处理大事计")
@Controller
public class EventController {

    /**
     * 权限测试
     * @return
     */
    @ApiOperation(value = "event的delete权限")
    @ResponseBody
    @RequestMapping(value = "/event/delete", method = RequestMethod.POST)
    public ResponseDTO delete(){
        return new ResponseDTO(Constant.CODE_OK, "还有event/delete权限！");
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
