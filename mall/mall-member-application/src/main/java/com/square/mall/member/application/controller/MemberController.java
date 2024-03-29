package com.square.mall.member.application.controller;

import com.square.mall.common.dto.CommonRes;
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
 * 会员Controller
 *
 * @author Gencent
 * @date 2020/7/3
 */
@RestController
@RequestMapping(value = "/member")
@Slf4j
@Validated
@Api(tags = "会员")
public class MemberController {

    @Resource
    private MemberService memberService;

    /**
     * 根据手机号获取会员信息
     *
     * @param mobile 手机号
     * @return 会员信息
     */
    @GetMapping("/selectMemberByMobile")
    @ApiOperation(value = "根据手机号获取会员信息")
    @ApiImplicitParam(name = "mobile", value = "手机号码", paramType = "query", dataTypeClass = String.class,
        required = true, example = "13500000001")
    public CommonRes<MemberDto>  selectMemberByMobile(@RequestParam("mobile") @Pattern(regexp = "^1[345789][0-9]{9}$",
        message = "手机号格式不对") @NotBlank(message = "手机号不能为空") String mobile) {

        CommonRes<MemberDto> memberRspDto = memberService.selectMemberByMobile(mobile);
        log.info("memberRspDto: {}, mobile: {}", memberRspDto, mobile);

        return memberRspDto;

    }

    /**
     * 插入会员信息
     *
     * @param memberDto 会员信息
     * @return 数据库ID
     */
    @PostMapping("/insertMember")
    @ApiOperation(value = "插入会员信息")
    public CommonRes<Long> insertMember(@RequestBody @Valid MemberDto memberDto) {
        CommonRes<Long> id = memberService.insertMember(memberDto);
        log.info("id: {}, memberDto: {}", id.getData(), memberDto);
        return id;
    }

    /**
     * 根据手机号码更新会员信息
     *
     * @param memberDto 会员信息
     * @return 响应
     */
    @PutMapping("/updateMemberByMobile")
    @ApiOperation(value = "根据手机号码更新会员信息")
    public CommonRes<Void> updateMemberByMobile(@RequestBody @Valid MemberDto memberDto) {
       return memberService.updateMemberByMobile(memberDto);
    }


}