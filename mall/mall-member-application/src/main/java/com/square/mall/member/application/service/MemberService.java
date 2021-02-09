package com.square.mall.member.application.service;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.member.center.api.dto.MemberDto;

/**
 * 会员信息Service
 *
 * @author Gencent
 * @date 2020/7/13
 */
public interface MemberService {

    /**
     *  根据手机号查询会员信息
     *
     * @param mobile 手机号
     * @return 会员信息
     */
    CommonRes<MemberDto> selectMemberByMobile(String mobile);

    /**
     * 插入会员信息
     *
     * @param memberDto 会员信息
     * @return 数据库ID
     */
    CommonRes<Long> insertMember(MemberDto memberDto);

    /**
     * 根据手机号码更新会员信息
     *
     * @param memberDto 会员信息
     * @return 响应
     */
    CommonRes<Void> updateMemberByMobile(MemberDto memberDto);

}
