package xyz.yuelai.pojo.dto.in;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 李泽众
 * @date 2019/7/23-13:47
 */


@Data
@ToString
public class LoginDTO implements Serializable {
    private String username;
    private String password;
    private String code;
}
