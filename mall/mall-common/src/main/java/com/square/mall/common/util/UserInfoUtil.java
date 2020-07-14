package com.square.mall.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 *  用户信息工具类
 *
 * @author Gencent
 * @date 2020/7/13
 */
@Slf4j
public class UserInfoUtil {

    /**
     *  获取用户ID
     *
     * @return 用户ID
     */
    public static Long getUserId() {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String userId = request.getHeader("userId");
        return StringUtil.isBlank(userId) ? null : Long.parseLong(userId);

    }

}
