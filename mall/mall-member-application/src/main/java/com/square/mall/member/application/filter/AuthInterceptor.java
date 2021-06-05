package com.square.mall.member.application.filter;

import com.square.mall.cache.api.CacheService;
import com.square.mall.common.util.JwtUtil;
import com.square.mall.common.util.StringUtil;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

/**
 * 认证拦截器
 *
 *@author Gencent
 *@date 2020/7/20
 *
 */
@Slf4j
@NoArgsConstructor
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final String RESP_RESULT = "{\"code\":401,\"msg\":\"unauthorized!\"}";

    /**
     * 可直接访问的url
     */
    private final static Set<String> URI_WHITE_SET = new HashSet<>();

    static {
        URI_WHITE_SET.add("/error");
        URI_WHITE_SET.add("/login/login");
        URI_WHITE_SET.add("/login/generateAuthCode");
    }

    @Resource
    private CacheService cacheService;


    @Override
    public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler) throws Exception {

        boolean isOk = false;
        try {
            String uri = httpRequest.getRequestURI().replace(httpRequest.getContextPath(), "");
            log.info("preHandle uri: " + uri);
            // 可直接访问的uri
            if (URI_WHITE_SET.contains(uri)) {
                log.info("preHandle 可直接访问的uri: {}", uri);
                return true;
            }
            // 需要token才能访问的uri
            String token = httpRequest.getHeader("auth");
            if (StringUtil.isBlank(token)) {
                log.error("preHandle 消息头没有传auth参数！uri: {}", uri);
                return false;
            }
            String mobile = JwtUtil.getMobile(token);
            String key = "login:auth:token:" + mobile;
            isOk = checkToken(key, token);
            return isOk;
        } catch (Exception e) {
            log.error("preHandle error message", e);
        } finally {
            if (!isOk) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                PrintWriter out = httpResponse.getWriter();
                out.append(RESP_RESULT);
                out.close();
            }
        }
        return false;
    }


    /**
     *  验证token
     *
     * @param key 缓存Key
     * @param token 令牌
     * @return 是否严重通过
     */
    private boolean checkToken(String key, String token) {
        String tokenCache = cacheService.getCache(key, String.class);
        log.info("token: {}, tokenCache: {}", token, tokenCache);
        return token.equals(tokenCache);
    }

}

