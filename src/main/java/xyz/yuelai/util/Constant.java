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
     */
    public static final int CODE_OK = 10000;
    public static final int CODE_ERROR_PARAMS = 10001;
    public static final int CODE_UNAUTHORIZED = 10002;
    public static final int CODE_UNAUTHENTICATED = 10003;
    public static final int CODE_FORBIDDEN = 10004;
    public static final int CODE_SERVER_EXCEPTION = 10005;
    public static final int CODE_AUTHENTICATED = 10006;

}
