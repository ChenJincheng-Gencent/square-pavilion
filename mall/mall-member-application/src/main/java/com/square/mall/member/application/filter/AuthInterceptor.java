package com.square.mall.member.application.filter;

import com.square.mall.cache.api.CacheService;
import com.square.mall.common.util.JwtUtil;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
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

    private static final String RESP_RESULT = "{\"code\":401,\"msg\":\"请先登录！\"}";

    /**
     * 可直接访问url
     * */
    private static Set<String> uriWhiteSet;

    static {
        uriWhiteSet.add("/login");
        uriWhiteSet.add("/auth/code");
    }

    @Resource
    private CacheService cacheService;


    @Override
    public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler) throws Exception {
        boolean isContinue = false;
        try {
            String uri = httpRequest.getRequestURI().replace(httpRequest.getContextPath(), "");
            log.info("uri: " + uri);
            // 可直接访问的uri
            if (uriWhiteSet.contains(uri)) {
                isContinue = true;
                log.info("可直接访问的uri = {}", uri);
                return isContinue;
            }
            // 需要token才能访问的uri
            String authToken = httpRequest.getHeader("autho");
            if (StringUtils.isEmpty(authToken)) {
                return isContinue;
            }
            String mobile = JwtUtil.getMobile(authToken);
            isContinue = checkToken(mobile, authToken);
        } catch (Exception e) {
            log.error("error message", e);
        } finally {
            log.info("isContinue值为 {}", isContinue);
            if (!isContinue) {
                String status = String.valueOf(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.setStatus(Integer.parseInt(status));
                PrintWriter out = httpResponse.getWriter();
                out.append(RESP_RESULT);
                out.close();
            }
        }
        return isContinue;
    }


    /**
     * 验证token
     * @param key
     * @param authToken
     * @return 验证通过返回true  否则返回false
     */
    private boolean checkToken(String key, String authToken) {
        String authTokenCache = cacheService.getCache("login:auth:token:"+key, String.class);
        return authToken.equals(authTokenCache);
    }

}

