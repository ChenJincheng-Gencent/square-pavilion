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
    private static final String REPEAT_DATA_GROUP = "repeat-data";
    /**
     * 该事件内的重复表单验证时间(单位秒)
     */
    private static final Integer CACHE_TIME_OUT = 60;

    private final static List<String> WHITE_NAME_LIST = new ArrayList<>();

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
        log.error("表单重复提交验证开始");
        String method = "";
        String url = "";
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                url = request.getRequestURI();
                method = request.getMethod();
                if (REQUEST_METHOD.contains(method) && repeatDataValidator(request, joinPoint)) {
                    return new RspDto<>("-1", "系统正在为您处理上一次提交的内容，请稍候再提交。");
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        Object[] args = joinPoint.getArgs();
        if (!WHITE_NAME_LIST.contains(url) && REQUEST_METHOD.contains(method)) {
            replaceEmoji(args);
        }
        Object obj = null;
        try {
            ///执行方法，以新的参数（如果不带args就是用原先的参数；这里带不带都可以，上面方法获取原先参数的引用做的修改）
            obj = joinPoint.proceed(args);
        } catch (Exception e) {
            throw e;
        } finally {
            cacheService.delCache(REPEAT_DATA_GROUP, CACHE_KEY.get());
        }
        return obj;
    }

    /**
     * 替换表情等特殊字段
     * @param args
     */
    private void replaceEmoji(Object[] args) {
        try {
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    Object object = args[i];
                    if (object != null) {
                        if (object instanceof String) {
                            object = ((String) object).replaceAll("\\t|\\r|\\n|\\u202C","");
                            object = ((String) object).replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "?");
                            args[i] = object;
                            continue;
                        }

                        Class clazz = object.getClass();
                        if (clazz.getName().endsWith("Eo") || clazz.getName().endsWith("Dto")) {
                            Field[] fields = clazz.getDeclaredFields();
                            for (Field field : fields) {
                                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                                Method getMethod = pd.getReadMethod();
                                Object obj = getMethod.invoke(object);
                                if (obj instanceof String) {
                                    obj = ((String)obj).replaceAll("\\t|\\r|\\n|\\u202C","");
                                    obj = ((String) obj).replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "?");
                                    Method setMethod = pd.getWriteMethod();
                                    setMethod.invoke(object, obj);
                                }
                            }
                        }

                    }

                }
            }

        } catch (Exception e) {
            log.error("aop拦截异常，参数:{}", JSON.toJSONString(args), e);
        }

    }

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
        Map<String, String> map = new HashMap<>(1);
        map.put(url, params);
        String nowUrlParams = Md5Util.getMd5(userId + ":" + map.toString());
        CACHE_KEY.set(nowUrlParams);

        String preUrlParams = cacheService.getCache(REPEAT_DATA_GROUP, nowUrlParams, String.class);
        if (preUrlParams == null) {
            Long t = cacheService.setnx(REPEAT_DATA_GROUP, nowUrlParams, CACHE_TIME_OUT.toString());
            cacheService.expire(REPEAT_DATA_GROUP, nowUrlParams, CACHE_TIME_OUT);
            return null != t && t == 0;
        } else {
            return true;
        }
    }

}
