package com.softeem.utils;

import java.security.MessageDigest;

public class MD5Util {

    /**
     * 对传入的String进行MD5加密
     * @param s
     * @return
     */
    public static final String getMd5(String s) {
        //16禁止数组
        char[] hexDigits = {
                '5','0','5','6','s','o','f','t','e','e','m','b','x','y','9','0'
        };

        try {
            //把传入的字符串成byte数组
            byte[] strTemp = s.getBytes();
            //获取MD5加密对象
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            //传入需要加密的目标数组
            md5.update(strTemp);
            //获取加密后的数组
            byte[] md = md5.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte bt = md[i];
                str[k++] = hexDigits[bt >>> 4 & 0xf];
                str[k++] = hexDigits[bt & 0xf];
            }
            //转换成数组并返回
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(getMd5("123456"));
    }
}
