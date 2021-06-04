package com.square.mall.member.center.biz.controller;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.member.center.api.LoginApi;
import com.square.mall.member.center.api.dto.LoginDto;
import com.square.mall.member.center.biz.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录Controller
 *
 * @author Gencent
 * @date 2020/11/11
 */
@RestController
@RequestMapping("/login")
@Api(tags = "登录")
public class LoginController implements LoginApi {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     *
     * @param loginDto 登录信息
     * @return token
     */
    @Override
    @PostMapping("/insertLogin")
    @ApiOperation("登录")
    public CommonRes<Long> insertLogin(@RequestBody LoginDto loginDto) {
        return new CommonRes<>(loginService.insertLogin(loginDto));
    }
}
