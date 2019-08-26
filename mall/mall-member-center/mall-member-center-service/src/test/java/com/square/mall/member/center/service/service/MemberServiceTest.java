package com.square.mall.member.center.service.service;

import com.square.mall.member.center.api.dto.response.MemberRsp;
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
    public void findMemberByMobile(){

        MemberRsp memberRsp = memberService.findMemberByMobile("123");
        Assert.assertNull(memberRsp);

    }


}
