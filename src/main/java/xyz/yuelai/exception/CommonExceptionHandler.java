package xyz.yuelai.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.util.Constant;

/**
 * @author 李泽众
 * @date 2019/7/13-18:11
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseDTO handle(Exception e){
        e.printStackTrace();
        return new ResponseDTO(Constant.CODE_SERVER_EXCEPTION, e.getMessage());
    }
}
