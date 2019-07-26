package xyz.yuelai.util;

import org.apache.shiro.crypto.hash.Md5Hash;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author 李泽众
 * @date 2019/7/13-13:03
 */


public class EncryptUtil {

    public static String encrypt(String password, String username) {
        return new Md5Hash(password, username, 2).toHex();
    }

    public static void main(String[] args) {
        System.out.println(EncryptUtil.encrypt("123456", "xizi"));
    }
}
