package com.itheima.tliaswebmanagement.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
//@WebFilter("/2")
public class DemoFilter implements Filter { // 过滤器的顺序是按照类名称排序的, D 开头的就没有 AbcFilter 早, 因为 A 字典序早
    // 1. 判断是否是登录请求,
    // 2. 如果是, 放行, 不是, 校验

    @Override // 初始化方法,只会被调用 1 次
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("Init ");
    }

    @Override // 每次拦截到请求后都会被调用
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截到了请求");
        // 放行操作
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override // 销毁方法,只调用 1 次
    public void destroy() {
        Filter.super.destroy();
        System.out.println("Destroy ");
    }
}
