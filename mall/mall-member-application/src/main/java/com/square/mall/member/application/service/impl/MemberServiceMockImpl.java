package com.square.mall.member.application.service.impl;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.member.application.service.MemberService;
import com.square.mall.member.center.api.dto.MemberDto;
import com.square.mall.member.center.api.enums.MemberLevel;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * 会员信息查询Service Mock实现类
 * @author Gencent
 * @date 2020/7/10
 */
@Service
@Profile("local")
@Primary
public class MemberServiceMockImpl implements MemberService {

    @Override
    public CommonRes<MemberDto> selectMemberByMobile(String mobile) {
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail("402634287@qq.com");
        memberDto.setGender(1);
        memberDto.setLevel(MemberLevel.STAR.getValue());
        memberDto.setMobile("150xxxxxxxx");
        memberDto.setName("Gencent");
        return new CommonRes<>(memberDto);
    }

    @Override
    public CommonRes<Long> insertMember(MemberDto memberDto) {
        return new CommonRes<>(0L);
    }

    @Override
    public CommonRes<Void> updateMemberByMobile(MemberDto memberDto) {
        return CommonRes.SUCCESS;
    }

}
