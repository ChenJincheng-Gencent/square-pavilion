package com.square.mall.member.center.service.service;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.center.api.dto.LoginDto;

/**
 * 登录信息Service
 *
 * @author Gencent
 * @date 2020/7/17
 */
public interface LoginService {

    /**
     * 插入登录信息
     *
     * @param loginDto 登录信息
     * @return 数据库ID
     */
    Long insertLogin(LoginDto loginDto);

}
