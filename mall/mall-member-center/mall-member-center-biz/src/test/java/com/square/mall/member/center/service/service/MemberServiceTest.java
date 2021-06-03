package com.square.mall.member.center.service.service;


import com.square.mall.member.center.api.dto.MemberDto;
import com.square.mall.member.center.biz.service.MemberService;
import org.junit.Assert;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class MemberServiceTest {

    //@Resource
    private MemberService memberService;

    //@Test
    public void selectMemberByMobile(){

        MemberDto memberDto = memberService.selectMemberByMobile("123");
        Assert.assertNull(memberDto);

    }


}
