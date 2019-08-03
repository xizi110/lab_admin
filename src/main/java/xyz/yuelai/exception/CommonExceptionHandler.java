package xyz.yuelai.exception;

import lombok.extern.log4j.Log4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.util.Constant;

/**
 * @author 李泽众
 * @date 2019/7/13-18:11
 */
@Log4j
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseDTO handle(Exception e){
        e.printStackTrace();
        return new ResponseDTO(Constant.CODE_SERVER_EXCEPTION, e.getMessage());
    }

    /**
     * 无权操作异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public ResponseDTO handle1(Exception e){
        log.info(e.getMessage());
        /* e.printStackTrace(); */
        return new ResponseDTO(Constant.CODE_SERVER_EXCEPTION, "您没有权限操作这个页面！");
    }

    /**
     * 文件上传大小异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    @ResponseBody
    public ResponseDTO handle2(Exception e){
        log.info(e.getMessage());
        /* e.printStackTrace(); */
        return new ResponseDTO(Constant.CODE_SERVER_EXCEPTION, "上传的文件大小超出限制，请上传1MB以内的文件！");
    }
}
