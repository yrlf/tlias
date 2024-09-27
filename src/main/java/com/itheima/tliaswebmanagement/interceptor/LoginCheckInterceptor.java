package com.itheima.tliaswebmanagement.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itheima.tliaswebmanagement.pojo.Result;
import com.itheima.tliaswebmanagement.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    // 返回为 True 则放行, 否则拦截, 这里设置登录校验的逻辑
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        System.out.println("preHandle运行了..");

        // 打印所有请求头
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = req.getHeader(headerName);
            log.info("请求头: {} = {}", headerName, headerValue);
        }

        // 1. 获取 url
        String url = req.getRequestURI().toString();
        log.info("请求 url {} ", url);

        // 2. 如果是登录请求, 放行
        if (url.contains("login")){
            log.info("登录操作, 放行");

            return true;
        }

        // 3. 获取请求头中的 token
        String jwt = req.getHeader("token");
        // 4. 如果 jwt 不存在返回错误信息
        if (!StringUtils.hasLength(jwt)){
            log.info("token 为空, 返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            // 手动转换 将对象转成 json, 利用 alibaba fastjson
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }
        // 5 . 解析校验 jwt
        try{
            JwtUtils.parseJWT(jwt);
        } catch (Exception e){
            e.printStackTrace();
            log.info("解析令牌失败, 返回未登录的错误信息");
            Result error = Result.error("NOT_LOGIN");
            // 手动转换 将对象转成 json, 利用 alibaba fastjson
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }

        //6. 放行
        log.info("令牌合法, 放行");
        return true;
    }




    // 目标资源运行之后运行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle运行了..");

    }
    // 最后运行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion运行了..");
    }
}
