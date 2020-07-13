package com.square.mall.member.application.service.impl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.member.MemberLevel;
import com.square.mall.member.application.service.MemberQueryService;
import com.square.mall.member.center.api.dto.response.MemberRspDto;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * 会员信息查询Service Mock实现类
 * @author Gencent
 * @date 2020/7/10
 */
@Service
@Profile("member-application-mock")
@Primary
public class MemberQueryServiceMockImpl implements MemberQueryService {

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