package com.square.mall.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.square.mall.cache.api.CacheService;
import com.square.mall.common.util.JwtUtil;
import com.square.mall.common.util.StringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;


/**
 * 网关统一的token验证
 *
 * @author Gencent
 * @date 2021/6/8
 */
@Slf4j
@Component
@AllArgsConstructor
public class UaaFilter implements GlobalFilter, Ordered {

    private final static Boolean AUTH_SWITCH = true;

    /**
     * 可直接访问的url
     */
    private final static Set<String> URI_WHITE_SET = new HashSet<>();

    static {
        URI_WHITE_SET.add("/error");
        URI_WHITE_SET.add("/actuator/**");
        URI_WHITE_SET.add("/v2/api-docs/**");
        URI_WHITE_SET.add("/v2/api-docs-ext/**");
        URI_WHITE_SET.add("/swagger/api-docs");
        URI_WHITE_SET.add("/swagger-ui.html");
        URI_WHITE_SET.add("/doc.html");
        URI_WHITE_SET.add("/swagger-resources/**");
        URI_WHITE_SET.add("/webjars/**");
        URI_WHITE_SET.add("/login/login");
        URI_WHITE_SET.add("/login/generateAuthCode");

    }

    @Resource
    private final CacheService cacheService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 如果未启用网关验证，则跳过
        if (!AUTH_SWITCH) {
            return chain.filter(exchange);
        }

        //　如果在忽略的url里，则跳过
        String path = replacePrefix(exchange.getRequest().getURI().getPath());
        String requestUrl = exchange.getRequest().getURI().getRawPath();
        if (ignore(path) || ignore(requestUrl)) {
            return chain.filter(exchange);
        }

        // 验证token是否有效
        ServerHttpResponse resp = exchange.getResponse();
        String token = exchange.getRequest().getHeaders().getFirst("Auth");
        String memberIdStr = exchange.getRequest().getHeaders().getFirst("MemberId");
        if (StringUtil.isBlank(token) || StringUtil.isBlank(memberIdStr)) {
            log.error("filter 消息头没有传auth参数！requestUrl: {}, token: {}, memberIdStr: {}", requestUrl, token, memberIdStr);
            return unauthorized(resp, "没有携带Token信息！");
        }
        String mobile = JwtUtil.getMobile(token);
        String key = "LOGIN:AUTH:TOKEN:" + mobile;
        boolean isOk = checkToken(key, token, mobile, Long.parseLong(memberIdStr));

        if (!isOk) {
            return unauthorized(resp, "token已过期或验证不正确！");
        }
        return chain.filter(exchange);
    }

    /**
     * 检查是否忽略url
     * @param path 路径
     * @return boolean
     */
    private boolean ignore(String path) {
        return URI_WHITE_SET.stream()
                .map(url -> url.replace("/**", ""))
                .anyMatch(path::startsWith);
    }

    private Mono<Void> unauthorized(ServerHttpResponse res, String msg) {
        JSONObject message = new JSONObject();
        message.put("msg", msg);
        byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = res.bufferFactory().wrap(bits);
        res.setStatusCode(HttpStatus.UNAUTHORIZED);
        res.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return res.writeWith(Mono.just(buffer));

    }

    @Override
    public int getOrder() {
        return -100;
    }

    /**
     *  验证token
     *
     * @param key 缓存Key
     * @param token 令牌
     * @param mobile 手机号
     * @param memberId 用户ID
     * @return 是否严重通过
     */
    private boolean checkToken(String key, String token, String mobile, Long memberId) {
        String tokenCache = cacheService.getCache(key, String.class);
        log.info("checkToken token: {}, tokenCache: {}", token, tokenCache);
        if (!token.equals(tokenCache)) {
            return false;
        }
        log.info("checkToken mobile: {}, memberId: {}", mobile, memberId);
        return JwtUtil.verify(token, mobile, memberId);

    }

    /**
     * 移除模块前缀
     * @param path 路径
     * @return String
     */
    private String replacePrefix(String path) {
        if (path.startsWith("/trade-application") || path.startsWith("/member-application")
            || path.startsWith("/item-application") || path.startsWith("/manager-application")
            || path.startsWith("/job-application") || path.startsWith("/trade-center")
            || path.startsWith("/member-center") || path.startsWith("/item-center")
            || path.startsWith("/inventory-center") || path.startsWith("/promotion-center")
            || path.startsWith("/interaction-center") || path.startsWith("/logistics-center")
            || path.startsWith("/storage-center") || path.startsWith("/shop-center")
            || path.startsWith("/interface-center") || path.startsWith("/ticket-center")) {
            return path.substring(path.indexOf("/",1));
        }
        return path;
    }

}
