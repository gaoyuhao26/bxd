package com.softeem.interceptor;

import com.softeem.dto.EmployeeDTO;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录权限拦截器
 *
 * @author Administrator
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        //从session里面获取`user`对应的对象
        Object userObj = request.getSession().getAttribute("user");
        if (userObj != null) {
            //如果用户信息不为null,则将session中的用户信息转换成EmployeeDTO实体对象
            EmployeeDTO userInfo = (EmployeeDTO) userObj;
            //空值以及有效判断(必须在职)
            if (userInfo != null && userInfo.getEmId() != null && userInfo.getStatus() == 1) {
                if (requestURI.toLowerCase().indexOf("login") >= 0 || "/".equals(requestURI)) {
                    response.sendRedirect("/main");
                    return false;
                }
                return true;
            }
        }
        if (requestURI.toLowerCase().indexOf("login") >= 0 || "/".equals(requestURI)) {
            return true;
        }
        //如果不满足登录验证的条件,就跳转到登录页面
        response.sendRedirect("/login");
        return false;
    }
}
