package com.square.mall.member.center.service.apiimpl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.DatabaseUtil;
import com.square.mall.common.util.ModuleConstant;
import com.square.mall.member.center.api.MemberApi;
import com.square.mall.member.center.api.dto.MemberDto;
import com.square.mall.member.center.service.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 会员API实现类
 *
 * @author Gencent
 * @date 2019/8/19
 */
@Slf4j

@Transactional(rollbackFor = Exception.class)
public class MemberApiImpl implements MemberApi {

    @Resource
    private MemberService memberService;

    @Override
    public RspDto<Long> insertMember(MemberDto memberDto) {
        int success = memberService.insertMember(memberDto);
        return DatabaseUtil.getResult(success, memberDto.getId(), ModuleConstant.MEMBER_CENTER);
    }

    @Override
    public RspDto updateMemberByMobile(MemberDto memberDto) {
        int success = memberService.updateMemberByMobile(memberDto);
        return DatabaseUtil.getResult(success, ModuleConstant.MEMBER_CENTER);
    }

}
