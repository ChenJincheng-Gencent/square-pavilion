package com.square.mall.member.application.service.impl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.application.service.MemberService;
import com.square.mall.member.center.api.MemberApi;
import com.square.mall.member.center.api.dto.response.MemberRspDto;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * 会员Service实现类
 * @author Gencent
 * @date 2020/7/10
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Reference
    private MemberApi memberApi;

    @Override
    public RspDto<MemberRspDto> selectMemberByMobile(String mobile) {
        //int a = 1/0;
        return memberApi.findMemberByMobile(mobile);
    }

}
