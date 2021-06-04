package com.square.mall.member.center.api;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.member.center.api.dto.MemberDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 会员API
 *
 * @author Gencent
 * @date 2019/8/26
 */
@FeignClient(name = "mall-member-center")
public interface MemberApi {

    /**
     * 插入会员信息
     *
     * @param memberDto 会员信息
     * @return 数据库ID
     */
    @PostMapping("/member/insertMember")
    CommonRes<Long> insertMember(@RequestBody MemberDto memberDto);

    /**
     * 根据手机号码更新会员信息
     *
     * @param memberDto 会员信息
     * @return 响应
     */
    @PutMapping("/member/updateMemberByMobile")
    CommonRes<Void> updateMemberByMobile(@RequestBody MemberDto memberDto);

    /**
     * 根据手机号码查询会员信息
     *
     * @param mobile 手机号码
     * @return 会员信息
     */
    @GetMapping("/member/selectMemberByMobile")
    CommonRes<MemberDto> selectMemberByMobile(@RequestParam("mobile") String mobile);

}
