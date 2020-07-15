package com.square.mall.member.application.controller;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.StringUtil;
import com.square.mall.member.application.service.MemberQueryService;
import com.square.mall.member.application.service.MemberService;
import com.square.mall.member.center.api.dto.MemberDto;
import com.square.mall.member.center.api.dto.response.MemberRspDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

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
@Validated
public class MemberController {

    @Resource
    private MemberQueryService memberQueryService;

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
    public RspDto selectMemberByMobile(@RequestParam("mobile") @Pattern(regexp = "^1[345789][0-9]{9}$", message = "手机号必须满足格式") String mobile) {

        if (StringUtil.isBlank(mobile)) {
            log.error("mobile is null or empty.");
            return RspDto.FAILED;
        }

        RspDto<MemberRspDto> memberRspDto = memberQueryService.selectMemberByMobile(mobile);
        log.info("memberRspDto: {}, mobile: {}", memberRspDto, mobile);

        return memberRspDto;

    }

    /**
     * 插入会员信息
     *
     * @param memberDto 会员信息
     * @return 数据库ID
     */
    @PostMapping("/member/info")
    @ResponseBody
    public RspDto insertMember(@RequestBody @Valid MemberDto memberDto) {
        RspDto<Long> id = memberService.insertMember(memberDto);
        log.info("id: {}, memberDto: {}", id.getData(), memberDto);
        return id;
    }


}