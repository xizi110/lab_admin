package xyz.yuelai.service;

import xyz.yuelai.pojo.domain.UserDO;
import xyz.yuelai.pojo.dto.ResponseDTO;

/**
 * @author 李泽众
 * @date 2019/7/13-13:28
 */


public interface IUserService {

    ResponseDTO getUserByUsername(String username);

}
