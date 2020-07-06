package com.square.mall.member.application.service.Impl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.member.MemberLevel;

import com.square.mall.member.application.service.MemberService;
import com.square.mall.member.center.api.dto.response.MemberRspDto;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("member-application-mock")
@Primary
public class MemberMockServiceImpl implements MemberService {

    @Override
    public RspDto<MemberRspDto> selectMemberByMobile(String mobile) {
        MemberRspDto memberRspDto = new MemberRspDto();
        memberRspDto.setEmail("402634287@qq.com");
        memberRspDto.setGender(1);
        memberRspDto.setLevel(MemberLevel.STAR.getValue());
        memberRspDto.setMobile("150xxxxxxxx");
        memberRspDto.setName("Gencent");
        return new RspDto<>(memberRspDto);
    }

}
