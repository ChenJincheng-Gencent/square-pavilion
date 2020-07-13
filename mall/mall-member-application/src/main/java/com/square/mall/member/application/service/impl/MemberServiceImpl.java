package com.square.mall.member.application.service.impl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.application.service.MemberService;
import com.square.mall.member.center.api.MemberApi;
import com.square.mall.member.center.api.dto.MemberDto;
import org.apache.dubbo.config.annotation.Reference;

/**
 *  会员信息Service实现类
 *
 * @author Gencent
 * @date 2020/7/13
 */
public class MemberServiceImpl implements MemberService {

    @Reference
    private MemberApi memberApi;

    @Override
    public RspDto<Long> insertMember(MemberDto memberDto) {
        return memberApi.insertMember(memberDto);
    }

}
