package com.square.mall.member.center.service.apiimpl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.center.api.LoginApi;
import com.square.mall.member.center.api.dto.LoginDto;
import com.square.mall.member.center.service.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  登录信息API实现类
 *
 * @author Gencent
 * @date 2020/7/17
 */
@Slf4j

@Transactional(rollbackFor = Exception.class)
public class LoginApiImpl implements LoginApi {

    @Resource
    private LoginService loginService;

    @Override
    public RspDto<Long> insertLogin(LoginDto loginDto) {
        return new RspDto<>(loginService.insertLogin(loginDto));
    }

}
