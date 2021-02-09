package com.square.mall.member.application.service;


import com.square.mall.common.dto.CommonRes;

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
    CommonRes<String> login(String mobile, String authCode);

    /**
     *  登出
     *
     * @param mobile 手机号
     * @return 响应
     */
    CommonRes<Void> loginOut(String mobile);

    /**
     * 生成短信验证码
     *
     * @param mobile 手机号
     * @return 响应
     */
    CommonRes<Void> generateAuthCode(String mobile);

}
