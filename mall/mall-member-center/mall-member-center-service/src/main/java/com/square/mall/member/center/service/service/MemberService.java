package com.square.mall.member.center.service.service;

import com.square.mall.member.center.api.dto.response.MemberRspDto;

/**
 * 会员基本信息Service
 *
 * @author Gencent
 * @date 2019/8/26
 */
public interface MemberService {

    /**
     * 根据手机号码查询会员信息
     *
     * @param mobile 手机号码
     * @return 会员基本信息
     */
    MemberRspDto findMemberByMobile(String mobile);

}