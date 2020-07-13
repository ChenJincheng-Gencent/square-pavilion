package com.square.mall.member.application.service;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.center.api.dto.MemberDto;

/**
 * 会员信息Service
 *
 * @author Gencent
 * @date 2020/7/13
 */
public interface MemberService {

    /**
     * 插入会员信息
     *
     * @param memberDto 会员信息
     * @return 数据库ID
     */
    RspDto<Long> insertMember(MemberDto memberDto);

}
