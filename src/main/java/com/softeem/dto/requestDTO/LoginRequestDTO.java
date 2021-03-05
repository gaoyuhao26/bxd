package com.softeem.dto.requestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
/**
 * @author Uesugi
 */
@ApiModel(description = "登录请求的DTO")
public class LoginRequestDTO implements Serializable {

    private static final long serialVersionUID = 6123862434504850517L;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名",required = true,example = "13100000001")
    private String userName;
    @ApiModelProperty(value = "密码",required = true,example = "123456")
    private String password;
    @ApiModelProperty(value = "是否需要输入验证码",required = false,example = "false",hidden = true)
    private Boolean needVerify;
    @ApiModelProperty(value = "用户输入的验证码",required = false,hidden = true)
    private String verifyCodeActual;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getNeedVerify() {
        return needVerify;
    }

    public void setNeedVerify(Boolean needVerify) {
        this.needVerify = needVerify;
    }

    public String getVerifyCodeActual() {
        return verifyCodeActual;
    }

    public void setVerifyCodeActual(String verifyCodeActual) {
        this.verifyCodeActual = verifyCodeActual;
    }
}
