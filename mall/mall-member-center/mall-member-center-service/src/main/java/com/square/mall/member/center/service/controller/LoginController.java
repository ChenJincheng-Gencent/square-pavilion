package com.square.mall.member.center.service.controller;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.center.api.dto.LoginDto;
import com.square.mall.member.center.service.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     *
     * @param loginDto 登录信息
     * @return token
     */
    @PostMapping("")
    public RspDto<Long> insertLogin(@RequestBody LoginDto loginDto) {
        return new RspDto<>(loginService.insertLogin(loginDto));
    }
}
