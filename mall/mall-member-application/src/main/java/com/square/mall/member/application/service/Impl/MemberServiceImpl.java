package com.square.mall.member.application.service.Impl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.application.service.MemberService;
import com.square.mall.member.center.api.MemberApi;
import com.square.mall.member.center.api.dto.response.MemberRspDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired(required = false)
    private MemberApi memberApi;

    @Override
    public RspDto<MemberRspDto> selectMemberByMobile(String mobile) {
        //int a = 1/0;
        return memberApi.findMemberByMobile(mobile);
    }

}
