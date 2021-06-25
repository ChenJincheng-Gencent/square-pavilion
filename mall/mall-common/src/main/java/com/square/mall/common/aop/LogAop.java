package com.square.mall.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * 日志切面
 *
 * @author Gencent
 * @date 2021/6/25
 */
@Component
@Aspect
@Slf4j
@Order(10)
public class LogAop {

    /**
     * 开始时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 当前请求
     */
    ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<>();

    /**
     * 定义切入点，controller下面的所有类的所有公有方法
     */
    @Pointcut("execution(public * com.square.mall.*.*.controller..*.*(..))")
    public void requestLog() {
    }


    /**
     * 方法之前执行，日志打印请求信息
     *
     * @param joinPoint joinPoint
     */
    @Before("requestLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
        try {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            RequestContextHolder.setRequestAttributes(servletRequestAttributes, true);
            HttpServletRequest request = servletRequestAttributes.getRequest();
            currentRequest.set(request);

            // 添加请求头信息
            Enumeration<String> headerNames = request.getHeaderNames();
            StringBuilder sb = new StringBuilder();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                sb.append(headerName)
                        .append("=")
                        .append(request.getHeader(headerName))
                        .append(";");
            }
            String methodSign = joinPoint.getSignature().getName();

            log.info("doBefore RequestMapping:[{}], RequestHeader:[{}], RequestParam:[{}],RequestMethod:[{}],MethodName:[{}] , RequestURL:[{}]",
                request.getRequestURI(), sb.toString(), Arrays.toString(joinPoint.getArgs()), request.getMethod(), methodSign, request.getRequestURL());
        }catch (Exception e){
            log.error("doBefore 日志打印异常",e);
        }
    }

    /**
     * 方法返回之前执行，打印才返回值以及方法消耗时间
     *
     * @param response 返回值
     */
    @AfterReturning(returning = "response", pointcut = "requestLog()")
    public void doAfterRunning(Object response) {
        try {
            log.info("doAfterRunning RequestMapping:[{}], Response:[{}], spend times: [{}ms]", currentRequest.get()
                .getRequestURI(), response, System.currentTimeMillis() - startTime.get());
        }catch (Exception e){
            log.error("doAfterRunning 出参日志打印异常",e);
        }

        currentRequest.remove();
        startTime.remove();
    }

    /**
     * 异常返回
     *
     * @param ex 异常
     */
    @AfterThrowing(throwing="ex", pointcut = "requestLog()")
    public void doAfterThrowing(Throwable ex) {
        log.error("doAfterThrowing RequestMapping:[{}], throws throwable, spend times: [{}ms]", currentRequest.get()
            .getRequestURI(), System.currentTimeMillis() - startTime.get(), ex);

        currentRequest.remove();
        startTime.remove();
    }

}
