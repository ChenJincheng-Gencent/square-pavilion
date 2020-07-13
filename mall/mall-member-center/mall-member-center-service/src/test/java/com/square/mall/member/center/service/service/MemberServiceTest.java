package com.square.mall.member.center.service.service;

import com.square.mall.member.center.api.dto.response.MemberRspDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

    @Resource
    private MemberService memberService;

    @Test
    public void selectMemberByMobile(){

        MemberRspDto memberRspDto = memberService.selectMemberByMobile("123");
        Assert.assertNull(memberRspDto);

    }


}
