package com.softeem.utils;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 高玉好
 * @ClassName CodeUtil
 * @since 2021/1/25 7:45
 */
public class CodeUtil {

    /**
     * 校验验证码
     * @param request,verifyCodeActual
     * @return
     */
    public static boolean checkVerifyCode(HttpServletRequest request,String verifyCodeActual) {
        /**
         * 1. 获取用户输入的验证码
         * 2. 获取实际生成的验证码
         * 3. 对比
         */
        verifyCodeActual = verifyCodeActual.toUpperCase();
        String verifyCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY).toString().toUpperCase();
        if (verifyCodeActual == null || !verifyCodeActual.equals(verifyCodeExpected)) {
            return false;
        }
        return true;
    }
}
