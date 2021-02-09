package com.square.mall.member.application.controller;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.member.application.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 登录Controller
 *
 * @author Gencent
 * @date 2020/7/17
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/member/v1")
@Slf4j
@Validated
@Api(tags = "登录REST API")
public class LoginController {

    @Resource
    private LoginService loginService;


    /**
     * 手机号码登录
     *
     * @param mobile 手机号码
     * @return token
     */
    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "手机号码登录")
    @ApiImplicitParam(name = "mobile", value = "手机号码", paramType = "query", dataTypeClass = String.class,
        required = true, example = "13500000001")
    public CommonRes<String> login(@RequestParam("mobile") @Pattern(regexp = "^1[345789][0-9]{9}$",
        message = "手机号格式不对") @NotBlank(message = "手机号不能为空")String mobile, @RequestParam("authCode")
        @NotBlank(message = "验证码不能为空") String authCode) {

        log.info("mobile: {}, authCode: {}", mobile, authCode);
        CommonRes<String> token = loginService.login(mobile, authCode);
        log.info("token: {}, mobile: {}, authCode: {}", token, mobile, authCode);

        return token;

    }

    /**
     * 手机号码登出
     *
     * @param mobile 手机号码
     * @return 响应
     */
    @PostMapping("/login/out")
    @ResponseBody
    @ApiOperation(value = "手机号码登出")
    @ApiImplicitParam(name = "mobile", value = "手机号码", paramType = "query", dataTypeClass = String.class,
            required = true, example = "13500000001")
    public CommonRes<Void> loginOut(@RequestParam("mobile") @Pattern(regexp = "^1[345789][0-9]{9}$",
            message = "手机号格式不对") @NotBlank(message = "手机号不能为空")String mobile) {

        log.info("mobile: {}", mobile);
        return loginService.loginOut(mobile);

    }

    /**
     * 获取短信验证码
     *
     * @param mobile 手机号
     * @return 响应结果
     */
    @PostMapping("/auth/code")
    @ResponseBody
    @ApiOperation(value = "获取短信验证码")
    @ApiImplicitParam(name = "mobile", value = "手机号码", paramType = "query", dataTypeClass = String.class,
            required = true, example = "13500000001")
    public CommonRes<Void> generateAuthCode(@RequestParam("mobile") @Pattern(regexp = "^1[345789][0-9]{9}$",
            message = "手机号格式不对") @NotBlank(message = "手机号不能为空") String mobile) {

        return loginService.generateAuthCode(mobile);

    }

}