package com.square.mall.member.application.filter;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  跨域拦截器
 *
 * @author Gencent
 * @date 2019/8/31
 */
@WebFilter(urlPatterns = "/*")
@Component
public class CrossFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String origin = request.getHeader("origin");

        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Max-Age", "36000");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token");
        response.setHeader("Access-Control-Expose-Headers", "*");

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }

}