package com.square.mall.member.application.controller;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.StringUtil;
import com.square.mall.member.application.service.MemberService;
import com.square.mall.member.center.api.dto.response.MemberRspDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 会员信息Controller
 *
 * @author Gencent
 * @date 2020/7/3
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/member/v1")
@Slf4j
public class MemberController {

    @Resource
    private MemberService memberService;

    /**
     * 根据手机号获取会员信息
     *
     * @param mobile 手机号
     * @return 会员信息
     */
    @GetMapping("/member/info")
    @ResponseBody
    public RspDto selectMemberByMobile(String mobile) {

        if (StringUtil.isBlank(mobile)) {
            log.error("mobile is null or empty.");
            return RspDto.FAILED;
        }

        RspDto<MemberRspDto> memberRspDto = memberService.selectMemberByMobile(mobile);
        log.info("memberRspDto: {}", memberRspDto);

        return memberRspDto;

    }


}