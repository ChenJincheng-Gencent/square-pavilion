package com.square.mall.member.application.service.impl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.application.service.MemberQueryService;
import com.square.mall.member.center.api.dto.response.MemberRspDto;
import com.square.mall.member.center.api.query.MemberQueryApi;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * 会员信息查询Service实现类
 * @author Gencent
 * @date 2020/7/10
 */
@Service
public class MemberQueryServiceImpl implements MemberQueryService {

    @Reference
    private MemberQueryApi memberQueryApi;

    @Override
    public RspDto<MemberRspDto> selectMemberByMobile(String mobile) {
        return memberQueryApi.selectMemberByMobile(mobile);
    }

}
