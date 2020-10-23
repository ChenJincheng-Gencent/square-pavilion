package com.square.mall.member.application.api;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.center.api.dto.MemberDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
    @PostMapping("/member")
    RspDto<Long> insertMember(MemberDto memberDto);

    /**
     * 根据手机号码更新会员信息
     *
     * @param memberDto 会员信息
     * @return 响应
     */
    @PutMapping("/member")
    RspDto updateMemberByMobile(MemberDto memberDto);

    /**
     * 根据手机号码查询会员信息
     *
     * @param mobile 手机号码
     * @return 会员信息
     */
    @GetMapping("/member")
    RspDto<MemberDto> selectMemberByMobile(String mobile);

}
