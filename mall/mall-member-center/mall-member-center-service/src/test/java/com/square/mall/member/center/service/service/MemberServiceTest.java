package com.square.mall.member.center.service.service;


import com.square.mall.member.center.api.dto.MemberDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
