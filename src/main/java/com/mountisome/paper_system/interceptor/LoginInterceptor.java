package com.mountisome.paper_system.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    // 在目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // preHandle函数
        HttpSession session = request.getSession();
        if (session.getAttribute("loginName") == null) {
            // 没有登录
            request.getRequestDispatcher("/").forward(request, response);
            return false;
        }
        // 放行，访问目标资源
        return true;
    }

}
