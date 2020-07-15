package com.square.mall.member.application.handler;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.exception.BusinessException;
import com.square.mall.common.util.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

/**
 *  全局异常处理器
 *
 * @author Gencent
 * @date 2019/8/31
 */

@Slf4j
@Controller
@ControllerAdvice
@RequestMapping("/global/error")
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final HttpServletRequest request;

    /**
     * 统一处理参数校验异常
     * <pre>
     * 对象参数接收请求体校验不通过会抛出 MethodArgumentNotValidException
     * 普通参数校验校验不通过会抛出 ConstraintViolationException
     * 必填参数没传校验不通过会抛出 ServletRequestBindingException
     * 请求参数绑定到对象上校验不通过会抛出 BindException
     * </pre>
     */
    @ExceptionHandler({ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            ServletRequestBindingException.class,
            BindException.class})
    @ResponseBody
    public RspDto handleValidationException(Exception e) {
        String logMsg = getErrorLogMsg(e);
        String msg;
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException t = (MethodArgumentNotValidException) e;
            msg = getBindingResultMsg(t.getBindingResult());
        } else if (e instanceof BindException) {
            BindException t = (BindException) e;
            msg = getBindingResultMsg(t.getBindingResult());
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException t = (ConstraintViolationException) e;
            msg = t.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(","));
        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException t = (MissingServletRequestParameterException) e;
            msg = t.getParameterName() + " 不能为空";
        } else if (e instanceof MissingPathVariableException) {
            MissingPathVariableException t = (MissingPathVariableException) e;
            msg = t.getVariableName() + " 不能为空";
        } else {
            // 其他类型的错误当成未知异常处理
            return handleUnknownException(e);
        }
        log.error("参数校验不通过, {}, msg: {}", logMsg, msg);
        return new RspDto(ErrorCode.ME_APP_ME_PARA_ILLEGAL.getCode(), ErrorCode.ME_APP_ME_PARA_ILLEGAL.getMsg()
            + ":" + msg);
    }

    /**
     * 统一处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public RspDto handleBusinessException(BusinessException t) {
        String logMsg = getErrorLogMsg(t);
        log.error("捕获到业务异常, {}, msg: {}", logMsg, t.getMessage());
        return new RspDto("-1", t.getMessage());
    }

    /**
     * 统一处理未知异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RspDto handleUnknownException(Exception e) {
        String logMsg = getErrorLogMsg(e);
        // 未知异常
        log.error("捕获到未经处理的未知异常, {}", logMsg, e);
        return new RspDto("-1", e.toString());
    }


    /**
     * 异常信息应包含 url + queryString(若有) + 请求参数(这里只能拿到表单提交的参数) + username(若有)
     */
    private String getErrorLogMsg(Throwable t) {

        StringBuilder errorLogMsg = new StringBuilder();
        // url，包括查询 queryString
        errorLogMsg.append("url: ").append(request.getRequestURL().toString());
        if (StringUtils.isNotBlank(request.getQueryString())) {
            errorLogMsg.append("?").append(request.getQueryString());
        }
        // 获取参数，这里只能拿到查询参数和以表单形式提交的参数，requestBody 的拿不到
        Map<String, String[]> params = request.getParameterMap();
        if (params != null && !params.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            params.forEach((k, v) -> {
                builder.append(",").append(k).append("=").append(Arrays.toString(v));
            });
            errorLogMsg.append(", params:").append(builder.substring(1));
        }
        // 如果能获取到当前登录人信息，则添加到最前面
        String username = getUsername();
        if (StringUtils.isNotBlank(username)) {
            errorLogMsg.insert(0, "username: " + username + ", ");
        }
        return errorLogMsg.toString();
    }

    private String getBindingResultMsg(BindingResult result) {
        return result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
    }

    private String getUsername() {
        // 尝试拿当前登录人的 principal
        return Optional.ofNullable(request.getUserPrincipal())
                .map(Principal::getName)
                .orElse("");
    }

}
