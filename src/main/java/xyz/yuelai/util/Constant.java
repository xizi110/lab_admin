package xyz.yuelai.util;

/**
 * @author 李泽众
 * @date 2019/7/12-12:54
 *
 * 统一常量类
 */


public class Constant {

    /**
     * 字符串参数最大有效长度
     */
    public static final int LEGAL_STRING_LENGTH = 30;

    /**
     * 返回客户端的状态码
     * 10000：正常返回
     * 10001：请求参数错误
     * 10002：没有授权的
     * 10003：没有经过身份验证的
     * 10004：禁止访问
     * 10005：服务器出现异常
     * 10006：已经认证
     * 10007：token无效
     */
    public static final int CODE_OK = 10000;
    public static final int CODE_ERROR_PARAMS = 10001;
    public static final int CODE_UNAUTHORIZED = 10002;
    public static final int CODE_UNAUTHENTICATED = 10003;
    public static final int CODE_FORBIDDEN = 10004;
    public static final int CODE_SERVER_EXCEPTION = 10005;
    public static final int CODE_AUTHENTICATED = 10006;
    public static final int CODE_INVALID_TOKEN = 10007;
    public static final int CODE_NOT_OK = 10008;

    /**
     *  JWT-username
     */
    public static final String USERNAME = "username";

    /**
     *  JWT-currentTimeMillis
     */
    public final static String CURRENT_TIME_MILLIS = "currentTimeMillis";

    /**
     *  JWT-secret_key
     */
    public final static String SECRET_KEY = "LiZeZhong123";

    /**
     *  JWT-token expire time，单位分钟
     */
    public final static Integer TOKEN_EXPIRE_TIME = 30;


    /**
     *  JWT-token expire time，单位分钟
     */
    public final static Integer REDIS_TOKEN_EXPIRE_TIME = 60;


    /**
     * 默认每页显示30条数据
     */
    public static final Integer PAGE_COUNT = 30;

    public static final String USERID = "userId";
}
