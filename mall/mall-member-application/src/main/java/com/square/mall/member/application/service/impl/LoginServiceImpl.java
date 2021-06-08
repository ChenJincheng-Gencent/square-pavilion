package com.square.mall.member.application.service.impl;

import com.square.mall.cache.api.CacheService;
import com.square.mall.cache.constant.ExpireTimeConstant;
import com.square.mall.common.dto.CommonRes;
import com.square.mall.common.util.JwtUtil;
import com.square.mall.common.util.SequenceUtil;
import com.square.mall.common.util.SmsUtil;
import com.square.mall.member.application.enums.MemberApplicationBizCode;
import com.square.mall.member.application.exception.MemberApplicationBizException;
import com.square.mall.member.application.service.LoginService;

import com.square.mall.member.application.util.RedisKeyConstant;
import com.square.mall.member.center.api.LoginApi;
import com.square.mall.member.center.api.MemberApi;
import com.square.mall.member.center.api.dto.LoginDto;
import com.square.mall.member.center.api.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 登录Service实现类
 *
 * @author Gencent
 * @date 2020/7/17
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private MemberApi memberApi;

    @Resource
    private CacheService cacheService;

    @Resource
    private LoginApi loginApi;

    @Override
    public CommonRes<String> login(String mobile, String authCode) {

        String rawAuthCode = cacheService.getCache("login:auth:" + mobile, String.class);
        if (!authCode.equals(rawAuthCode)) {
            log.error("验证码错误！authCode: {}, rawAuthCode: {}", authCode, rawAuthCode);
            throw new MemberApplicationBizException(MemberApplicationBizCode.AUTH_CODE_ERROR);
        }

        Long memberId;
        MemberDto oldMemberDto = memberApi.selectMemberByMobile(mobile).getData();
        log.info("oldMemberDto: {}, mobile: {}", oldMemberDto, mobile);
        if (null == oldMemberDto) {
            log.warn("该用户第一次注册。mobile: {}", mobile);
            MemberDto memberDto = new MemberDto();
            memberDto.setMobile(mobile);
            memberId = memberApi.insertMember(memberDto).getData();
        }else {
            memberId = oldMemberDto.getId();
        }
        String token = JwtUtil.sign(mobile, memberId);
        cacheService.setCache("gateway", RedisKeyConstant.LOGIN_AUTH_TOKEN + mobile, token,
            ExpireTimeConstant.ONE_DAY);
        LoginDto loginDto = new LoginDto();
        loginDto.setMemberId(memberId);
        loginDto.setMobile(mobile);
        loginDto.setToken(token);
        loginApi.insertLogin(loginDto);
        return new CommonRes<>(token);
    }

    @Override
    public CommonRes<Void> loginOut(String mobile) {
        cacheService.delCache("gateway", RedisKeyConstant.LOGIN_AUTH_TOKEN + mobile);
        return CommonRes.SUCCESS;
    }

    @Override
    public CommonRes<Void> generateAuthCode(String mobile) {

        String authCode = SequenceUtil.getRandNum(6);
        String msg = "【四方阁平台】 "+ authCode +" (四方阁平台验证码，请完成验证)，如非本人操作，请忽略本短信。";
        SmsUtil.sendMessage(mobile, msg);
        cacheService.setCache("login:auth:" + mobile, authCode, ExpireTimeConstant.ONE_MINUTE);
        return CommonRes.SUCCESS;
    }

}
