//package com.itheima.tliaswebmanagement.filter;
//
//import ch.qos.logback.core.util.StringUtil;
//import com.alibaba.fastjson.JSONObject;
//import com.itheima.tliaswebmanagement.pojo.Result;
//import com.itheima.tliaswebmanagement.utils.JwtUtils;
//import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.StringUtils;
//
//import java.io.IOException;
//@Slf4j
//@WebFilter("/*")
//public class LoginCheckFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpServletResponse resp = (HttpServletResponse) servletResponse;
//
//        // 1. 获取 url
//        String url = req.getRequestURI().toString();
//        log.info("请求 url {} ", url);
//
//        // 2. 如果是登录请求, 放行
//        if (url.contains("login")){
//            log.info("登录操作, 放行");
//            filterChain.doFilter(req, resp);
//            return;
//        }
//
//        // 3. 获取请求头中的 token
//        String jwt = req.getHeader("token");
//        // 4. 如果 jwt 不存在返回错误信息
//        if (!StringUtils.hasLength(jwt)){
//            log.info("token 为空, 返回未登录信息");
//            Result error = Result.error("NOT_LOGIN");
//            // 手动转换 将对象转成 json, 利用 alibaba fastjson
//            String notLogin = JSONObject.toJSONString(error);
//            resp.getWriter().write(notLogin);
//            return;
//        }
//        // 5 . 解析校验 jwt
//        try{
//            JwtUtils.parseJWT(jwt);
//        } catch (Exception e){
//            e.printStackTrace();
//            log.info("解析令牌失败, 返回未登录的错误信息");
//            Result error = Result.error("NOT_LOGIN");
//            // 手动转换 将对象转成 json, 利用 alibaba fastjson
//            String notLogin = JSONObject.toJSONString(error);
//            resp.getWriter().write(notLogin);
//            return;
//        }
//
//        //6. 放行
//        log.info("令牌合法, 放行");
//        filterChain.doFilter(req, resp);
//        return;
//    }
//
//}
