package com.square.mall.member.center.biz.controller;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.common.util.StringUtil;
import com.square.mall.member.center.api.MemberApi;
import com.square.mall.member.center.api.dto.MemberDto;
import com.square.mall.member.center.biz.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 会员Controller
 *
 * @author Gencent
 * @date 2020/11/11
 */
@RestController
@RequestMapping("/member")
@Slf4j
@Api(tags = "会员")
public class MemberController implements MemberApi {

    @Resource
    private MemberService memberService;

    /**
     * 插入会员信息
     *
     * @param memberDto 会员信息
     * @return 数据库ID
     */
    @PostMapping("/insertMember")
    @Override
    @ApiOperation("插入会员信息")
    public CommonRes<Long> insertMember(@RequestBody MemberDto memberDto) {
        memberService.insertMember(memberDto);
        return new CommonRes<>(memberDto.getId());
    }

    /**
     * 更新会员信息
     *
     * @param memberDto 会员信息
     * @return 响应
     */
    @Override
    @PutMapping("/updateMemberByMobile")
    @ApiOperation("更新会员信息")
    public CommonRes<Void> updateMemberByMobile(@RequestBody MemberDto memberDto) {
        memberService.updateMemberByMobile(memberDto);
        return CommonRes.SUCCESS;
    }

    /**
     * 根据手机号获取会员信息
     *
     * @param mobile 手机号
     * @return 会员信息
     */
    @Override
    @GetMapping("/selectMemberByMobile")
    @ApiOperation("根据手机号获取会员信息")
    public CommonRes<MemberDto> selectMemberByMobile(@RequestParam("mobile") String mobile) {

        if (StringUtil.isBlank(mobile)) {
            log.error("mobile is blank.");
            return null;
        }

        return new CommonRes<>(memberService.selectMemberByMobile(mobile));
    }

}
