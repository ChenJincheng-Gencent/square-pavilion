package com.square.mall.member.application.service;

import com.square.mall.common.dto.RspDto;

/**
 * 登录Service
 *
 * @author Gencent
 * @date 2020/7/17
 */
public interface LoginService {

    /**
     *  登录
     *
     * @param mobile 手机号
     * @param authCode 验证码
     * @return token
     */
    RspDto<String> login(String mobile, String authCode);

    /**
     * 生成短信验证码
     *
     * @param mobile 手机号
     * @return 响应
     */
    RspDto generateAuthCode(String mobile);

}
