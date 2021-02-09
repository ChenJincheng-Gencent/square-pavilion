package com.square.mall.member.application.service.impl;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.member.application.service.MemberService;
import com.square.mall.member.center.api.MemberApi;
import com.square.mall.member.center.api.dto.MemberDto;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 *  会员信息Service实现类
 *
 * @author Gencent
 * @date 2020/7/13
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberApi memberApi;

    @Override
    public CommonRes<MemberDto> selectMemberByMobile(String mobile) {
        return memberApi.selectMemberByMobile(mobile);
    }

    @Override
    public CommonRes<Long> insertMember(MemberDto memberDto) {
        return memberApi.insertMember(memberDto);
    }

    @Override
    public CommonRes<Void> updateMemberByMobile(MemberDto memberDto) {
        return memberApi.updateMemberByMobile(memberDto);
    }

}
