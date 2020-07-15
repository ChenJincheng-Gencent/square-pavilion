package com.square.mall.member.application.aop;

import com.alibaba.fastjson.JSON;
import com.square.mall.cache.api.CacheService;
import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.Md5Util;
import com.square.mall.common.util.UserInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 重复表单验证
 *
 * @author Gencent
 * @date 2020/7/13
 */
@Aspect
@Order(2)
@Slf4j
@Component
public class RepeatDataAop {

    @Resource
    private CacheService cacheService;

    /**
     * 验证重复表单的请求方法
     */
    private static final List<String> REQUEST_METHOD = new ArrayList<>();
    /**
     * redis的group
     */
    private static final String REPEAT_DATA_GROUP = "member-application:repeat-data";
    /**
     * 该事件内的重复表单验证时间(单位秒)
     */
    private static final Integer CACHE_TIME_OUT = 60;

    /**
     * url白名单
     */
    private final static List<String> WHITE_NAME_LIST = new ArrayList<>();

    /**
     * 缓存key
     */
    private final static ThreadLocal<String> CACHE_KEY = new ThreadLocal<>();

    static {
        REQUEST_METHOD.add("POST");
        REQUEST_METHOD.add("PUT");
        REQUEST_METHOD.add("DELETE");

        WHITE_NAME_LIST.add("/api/v1/notify");
    }

    @Pointcut("execution(public * com.square.mall.member.application.controller.*Controller.*(..))")
    protected void repeatDataPoint() {

    }

    @Around("repeatDataPoint()")
    public Object repeatDataProcess(ProceedingJoinPoint joinPoint) throws Throwable {

        String method = "";
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                method = request.getMethod();
                if (REQUEST_METHOD.contains(method) && repeatDataValidator(request, joinPoint)) {
                    return new RspDto<>("-1", "系统正在为您处理上一次提交的内容，请稍候再提交。");
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        Object[] args = joinPoint.getArgs();
        Object obj;
        try {
            obj = joinPoint.proceed(args);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        } finally {
            if (REQUEST_METHOD.contains(method)) {
                cacheService.delCache(REPEAT_DATA_GROUP, CACHE_KEY.get());
            }
        }
        return obj;
    }


    /**
     * 重复表单数据校验
     *
     * @param request 请求
     * @param joinPoint 切入点
     * @return 是否重复提交
     */
    private boolean repeatDataValidator(HttpServletRequest request, ProceedingJoinPoint joinPoint) {
        Long userId = UserInfoUtil.getUserId();
        String params = JSON.toJSONString(request.getParameterMap());
        int paramsLength = 2;
        if (params.length() == paramsLength) {
            params = JSON.toJSONString(joinPoint.getArgs());
        }
        if (params.length() <= paramsLength) {
            return false;
        }
        String url = request.getRequestURI();
        if (WHITE_NAME_LIST.contains(url)) {
            return false;
        }

        String nowUrlParams = Md5Util.getMd5(userId + ":" + url + ":" + params);
        log.info("nowUrlParams: {}", nowUrlParams);
        CACHE_KEY.set(nowUrlParams);

        String preUrlParamsValue = cacheService.getCache(REPEAT_DATA_GROUP, nowUrlParams, String.class);
        log.info("preUrlParamsValue: {}", preUrlParamsValue);
        if (null != preUrlParamsValue) {
            return true;
        }
        return !cacheService.setCache(REPEAT_DATA_GROUP, nowUrlParams, CACHE_TIME_OUT, CACHE_TIME_OUT, null);
    }

}
