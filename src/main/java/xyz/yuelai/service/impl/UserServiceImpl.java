package xyz.yuelai.service.impl;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.yuelai.dao.IUserDAO;
import xyz.yuelai.pojo.domain.UserDO;
import xyz.yuelai.pojo.dto.ResponseDTO;
import xyz.yuelai.service.IUserService;
import xyz.yuelai.util.Constant;

/**
 * @author 李泽众
 * @date 2019/7/13-13:31
 */


@Service
public class UserServiceImpl implements IUserService {

    private IUserDAO userDAO;

    public UserServiceImpl(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public ResponseDTO getUserByUsername(String username) {

        int code ;
        String msg ;
        UserDO userDO = null ;

        if(StringUtils.isEmpty(username)){
            code = -1;
            msg = "用户名非法！";
        }else if(username.length() > Constant.LEGAL_STRING_LENGTH){
            code = -1;
            msg = "参数长度过长，请保证在" + Constant.LEGAL_STRING_LENGTH + "个字符以内！";
        }else {
            userDO = userDAO.getUserByUsername(username);
            code = 0;
            msg = "succeed";
        }
        return new ResponseDTO(code, msg, userDO);
    }
}
