package com.square.mall.member.center.service.controller;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.DatabaseUtil;
import com.square.mall.common.util.ModuleConstant;
import com.square.mall.common.util.StringUtil;
import com.square.mall.member.center.api.dto.MemberDto;
import com.square.mall.member.center.service.service.MemberService;
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
public class MemberController {

    @Resource
    private MemberService memberService;

    /**
     * 插入会员信息
     *
     * @param memberDto 会员信息
     * @return 数据库ID
     */
    @PostMapping("")
    public RspDto<Long> insertMember(@RequestBody MemberDto memberDto) {
        int success = memberService.insertMember(memberDto);
        return DatabaseUtil.getResult(success, memberDto.getId(), ModuleConstant.MEMBER_CENTER);
    }

    /**
     * 更新会员信息
     *
     * @param memberDto 会员信息
     * @return 响应
     */
    @PutMapping("")
    public RspDto updateMemberByMobile(@RequestBody MemberDto memberDto) {
        int success = memberService.updateMemberByMobile(memberDto);
        return DatabaseUtil.getResult(success, ModuleConstant.MEMBER_CENTER);
    }

    /**
     * 根据手机号获取会员信息
     *
     * @param mobile 手机号
     * @return 会员信息
     */
    @GetMapping("")
    public RspDto<MemberDto> selectMemberByMobile(@RequestParam("mobile") String mobile) {

        if (StringUtil.isBlank(mobile)) {
            log.error("mobile is blank.");
            return null;
        }

        return new RspDto<>(memberService.selectMemberByMobile(mobile));
    }

}
