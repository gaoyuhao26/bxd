package com.softeem.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softeem.dto.EmployeeDTO;
import com.softeem.dto.Result;
import com.softeem.dto.requestDTO.LoginRequestDTO;
import com.softeem.entity.Employee;
import com.softeem.service.EmployeeService;
import com.softeem.utils.CodeUtil;
import com.softeem.utils.HttpServletRequestUtil;
import com.softeem.utils.RedisTemplateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 高玉好
 * @ClassName IndexController
 * @since 2021/1/23 1:32
 */
@Api(tags = "登录登出模块")
@Controller
public class IndexController extends BaseController {


    @Resource
    private EmployeeService employeeService;

    /**
     * 登录路由
     *
     * @return
     */
    @ApiOperation(value = "登录", notes = "后台登录")
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @ApiOperation(value = "验证登录")
    @PostMapping("/checkLogin")
    @ResponseBody
    public Result checkLogin(@RequestBody LoginRequestDTO loginRequestDTO) {
        Result<Object> result = new Result<>();
        /**
         * 1. 判断是否需要验证码校验
         * 2. 如果需要就将用户输入的验证码信息与实际生成的验证码做对比
         */
        boolean needVerify = loginRequestDTO.getNeedVerify();
        if (needVerify && !CodeUtil.checkVerifyCode(request, loginRequestDTO.getVerifyCodeActual())) {
            result.setFlag(false);
            result.setMsg("输入了错误的验证码");
            return result;
        }
        //获取输入的用户名
        String userName = loginRequestDTO.getUserName();
        //获取输入的密码
        String password = loginRequestDTO.getPassword();
        //创建redisTemplateUtil
        RedisTemplateUtil redisTemplateUtil = new RedisTemplateUtil(redisTemplate);
        EmployeeDTO employeeDTO = null;
        try {
            if (userName.equals(redisTemplateUtil.get("userName")) && password.equals(redisTemplateUtil.get("password"))) {
                //如果查到,就将用户信息存进session中
                String user = (String) redisTemplateUtil.get("user");
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    employeeDTO = objectMapper.readValue(user, EmployeeDTO.class);
                    if(employeeDTO != null){
                        result.setFlag(true);
                        request.getSession().setAttribute("user", employeeDTO);
                        Cookie[] cookies = request.getCookies();
                        if(cookies != null && cookies.length > 0){
                            for (Cookie cookie : cookies){
                                cookie.setMaxAge(3600*24*3);
                            }
                        }
                    }
                } catch (JsonProcessingException e) {
                    result.setFlag(false);
                    result.setMsg(e.getMessage());
                }

            } else {
                //非空校验
                if (userName != null && password != null) {
                    //通过用户名和密码去查数据库
                    employeeDTO = employeeService.getEmInfoByUsernameAndPassword(userName, password);
                    if (null != employeeDTO) {
                        result.setFlag(true);
                        //如果查到,就将用户信息存进session中
                        request.getSession().setAttribute("user", employeeDTO);
                    } else {
                        result.setFlag(false);
                        result.setMsg("账号密码错误或者该员工已离职");
                    }

                } else {
                    result.setFlag(false);
                    result.setMsg("账号密码均不能为空");
                }
            }
        }catch (Exception e){
            result.setFlag(false);
            result.setMsg("服务器或数据库连接失败");
        }
        return result;
    }

    /**
     * 主页路由
     *
     * @return
     */
    @RequestMapping("/main")
    public String mainPage(HttpServletRequest request) {
        request.getSession().setAttribute("pageName", "待处理报销单");
        return "main";
    }

    @PostMapping("/logout")
    @ResponseBody
    public Result logout() {
        //销毁session
        request.getSession().invalidate();
        return new Result(true);
    }
}
