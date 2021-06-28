package com.square.mall.common.aop;

import com.alibaba.fastjson.JSON;
import com.square.mall.cache.api.CacheService;
import com.square.mall.common.dto.CommonRes;
import com.square.mall.common.enums.ErrorCode;
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
import java.util.ArrayList;
import java.util.List;

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
     * 键前缀
     */
    private static final String REPEAT_DATA = "REPEAT:DATA:";
    /**
     * 该事件内的重复表单验证时间(单位秒)
     */
    private static final Integer CACHE_TIME_OUT = 60;

    /**
     * 缓存key
     */
    private final static ThreadLocal<String> CACHE_KEY = new ThreadLocal<>();

    static {
        REQUEST_METHOD.add("POST");
        REQUEST_METHOD.add("PUT");
        REQUEST_METHOD.add("DELETE");
    }

    @Pointcut("execution(public * com.square.mall.*.*.controller.*Controller.*(..))")
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
                    return new CommonRes<>(ErrorCode.REPEAT_QUEST);
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
                cacheService.delCache(CACHE_KEY.get());
                CACHE_KEY.remove();
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

        String nowUrlParams = Md5Util.getMd5(userId + ":" + url + ":" + params);
        log.info("nowUrlParams: {}", nowUrlParams);
        CACHE_KEY.set(REPEAT_DATA + nowUrlParams);

        String preUrlParamsValue = cacheService.getCache(CACHE_KEY.get(), String.class);
        log.info("preUrlParamsValue: {}", preUrlParamsValue);
        if (null != preUrlParamsValue) {
            return true;
        }
        return !cacheService.setCache(CACHE_KEY.get(), CACHE_TIME_OUT, CACHE_TIME_OUT);
    }

}
