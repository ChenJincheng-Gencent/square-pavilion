package com.square.mall.member.center.biz.service;

import com.square.mall.member.center.api.dto.MemberDto;

/**
 * 会员Service
 *
 * @author Gencent
 * @date 2019/8/26
 */
public interface MemberService {

    /**
     * 根据手机号码查询会员信息
     *
     * @param mobile 手机号码
     * @return 会员信息
     */
    MemberDto selectMemberByMobile(String mobile);

    /**
     * 插入会员信息
     *
     * @param memberDto 会员信息
     * @return 是否成功,1成功，0失败
     */
    int insertMember(MemberDto memberDto);

    /**
     * 根据手机号码更新会员信息
     *
     * @param memberDto 会员信息
     * @return 是否成功,1成功，0失败
     */
    int updateMemberByMobile(MemberDto memberDto);

}
