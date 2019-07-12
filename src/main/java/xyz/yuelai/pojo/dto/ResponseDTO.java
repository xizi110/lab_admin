package xyz.yuelai.pojo.dto;

import lombok.Data;

/**
 * @author 李泽众
 * @date 2019/7/12-12:30
 *
 * 封装了返回给客户端请求的数据，响应码，响应信息
 *
 */


@Data
public class ResponseDTO {

    private Integer code;
    private String msg;
    private Object data;

    public ResponseDTO(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseDTO(Integer code, String msg) {
        this(code,msg,null);
    }


}
