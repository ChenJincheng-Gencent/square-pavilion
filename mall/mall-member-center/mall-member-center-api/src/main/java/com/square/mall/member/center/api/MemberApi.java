package com.square.mall.member.center.api;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.center.api.dto.MemberDto;

/**
 * 会员API
 *
 * @author Gencent
 * @date 2019/8/26
 */
public interface MemberApi {

    /**
     * 插入会员信息
     *
     * @param memberDto 会员信息
     * @return 数据库ID
     */
    RspDto<Long> insertMember(MemberDto memberDto);

    /**
     * 根据手机号码更新会员信息
     *
     * @param memberDto 会员信息
     * @return 响应
     */
    RspDto updateMemberByMobile(MemberDto memberDto);

}
