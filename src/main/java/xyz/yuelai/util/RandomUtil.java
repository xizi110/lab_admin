package xyz.yuelai.util;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author 李泽众
 * @date 2019/7/13-13:12
 */


public class RandomUtil {

    private static final String DEFAULT_AVAILABLE_STRING = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ`~!@#$%^&*()-_=+|\\;:'\",<>.?/";
    private static Random random ;

    public static String randString(int len){
        random = new Random(Calendar.getInstance().getTimeInMillis());
        StringBuilder randStr = new StringBuilder(len);
        for (int i = 0; i < len; i++){
            int index = random.nextInt(DEFAULT_AVAILABLE_STRING.length());
            randStr.append(DEFAULT_AVAILABLE_STRING.charAt(index));
        }
        return randStr.toString();
    }

    public static void main(String[] args) {
        String randString = RandomUtil.randString(30);
        System.out.println(randString);
    }
}
