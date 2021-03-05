package com.softeem.utils;



import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @author 高玉好
 * @ClassName DESUtils
 * @since 2021/1/25 6:59
 */
public class DESUtils {
    private static Key key;
    //设置自定义秘钥
    private static String KEY_STR = "softeem";
    //字符编码
    private static String CHARSETNAME = "UTF-8";
    //指定DES算法
    private static String ALGORITHM = "DES";

    static {
        try {
            //生成DES算法对象
            KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
            //运用指定安全策略SHA1
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            //设置秘钥的种子
            secureRandom.setSeed(KEY_STR.getBytes(StandardCharsets.UTF_8));
            //初始化基于安全策略的算法对象
            generator.init(secureRandom);
            //获取秘钥对象
            key = generator.generateKey();
            generator = null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取加密后的值
     */
    public static String getEncryptString(String oriStr){
        //基于BASE64编码,常用的就是将byte[]转换成String
        BASE64Encoder base64Encoder = new BASE64Encoder();
        try {
            byte[] bytes = oriStr.getBytes(StandardCharsets.UTF_8);
            //获取加密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //初始化加密对象
            cipher.init(Cipher.ENCRYPT_MODE,key);
            //加密
            byte[] doFinal = cipher.doFinal(bytes);
            return base64Encoder.encode(doFinal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    /**
     * 获取解密后的值
     */
    public static String getDecryptString(String encryptStr){
        BASE64Decoder base64Decoder = new BASE64Decoder();
        try {
            //将字符串decode成byte[]
            byte[] bytes = base64Decoder.decodeBuffer(encryptStr);
            //获取解密对象
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //初始化解密对象
            cipher.init(Cipher.DECRYPT_MODE,key);
            //解密
            byte[] doFinal = cipher.doFinal(bytes);
            return new String(doFinal, CHARSETNAME);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        //LG3dNFo3+/8=
        System.out.println(getEncryptString("root"));
        System.out.println(getDecryptString("LG3dNFo3+/8="));
    }
}
