package com.square.mall.member.application.controller;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.application.service.MemberService;
import com.square.mall.member.center.api.dto.MemberDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
@Api(tags = "会员REST API")
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
    @ApiOperation(value = "根据手机号获取会员信息")
    @ApiImplicitParam(name = "mobile", value = "手机号码", paramType = "query", dataTypeClass = String.class,
        required = true, example = "13500000001")
    public RspDto selectMemberByMobile(@RequestParam("mobile") @Pattern(regexp = "^1[345789][0-9]{9}$",
        message = "手机号格式不对") @NotBlank(message = "手机号不能为空") String mobile) {

        RspDto<MemberDto> memberRspDto = memberService.selectMemberByMobile(mobile);
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
    @ApiOperation(value = "插入会员信息")
    public RspDto insertMember(@RequestBody @Valid MemberDto memberDto) {
        RspDto<Long> id = memberService.insertMember(memberDto);
        log.info("id: {}, memberDto: {}", id.getData(), memberDto);
        return id;
    }

    /**
     * 根据手机号码更新会员信息
     *
     * @param memberDto 会员信息
     * @return 响应
     */
    @PutMapping("/member/info")
    @ResponseBody
    @ApiOperation(value = "根据手机号码更新会员信息")
    public RspDto updateMemberByMobile(@RequestBody @Valid MemberDto memberDto) {
       return memberService.updateMemberByMobile(memberDto);
    }


}