package com.square.mall.member.center.service.handler;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.exception.BusinessException;
import com.square.mall.common.util.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 *  全局异常处理器
 *
 * @author Gencent
 * @date 2019/8/31
 */

@Slf4j
@Service
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

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
    public RspDto handleValidationException(Exception e) {

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
        log.error("参数校验不通过, msg: {}", msg);
        return new RspDto(ErrorCode.ME_APP_ME_PARA_ILLEGAL.getCode(), ErrorCode.ME_APP_ME_PARA_ILLEGAL.getMsg()
                + ":" + msg);
    }

    /**
     * 统一处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public RspDto handleBusinessException(BusinessException t) {
        log.error("捕获到业务异常, msg: {}", t.getMessage());
        return new RspDto("-1", t.getMessage());
    }

    /**
     * 统一处理未知异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RspDto handleUnknownException(Exception e) {
        // 未知异常
        log.error("捕获到未经处理的未知异常", e);
        return new RspDto("-1", e.toString());
    }


    private String getBindingResultMsg(BindingResult result) {
        return result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
    }

}
