package com.square.mall.member.application.service.impl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.application.service.MemberService;
import com.square.mall.member.center.api.MemberApi;
import com.square.mall.member.center.api.dto.MemberDto;
import com.square.mall.member.center.api.query.MemberQueryApi;
import org.apache.dubbo.config.annotation.Reference;
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

    @Reference
    private MemberApi memberApi;

    @Resource
    private MemberQueryApi memberQueryApi;

    @Override
    public RspDto<MemberDto> selectMemberByMobile(String mobile) {
        return memberQueryApi.selectMemberByMobile(mobile);
    }

    @Override
    public RspDto<Long> insertMember(MemberDto memberDto) {
        return memberApi.insertMember(memberDto);
    }

}
