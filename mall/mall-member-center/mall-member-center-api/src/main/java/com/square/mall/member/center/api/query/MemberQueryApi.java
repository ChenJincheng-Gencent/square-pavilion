package com.square.mall.member.center.api.query;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.center.api.dto.response.MemberRspDto;

/**
 * 会员查询API
 * @author Gencent
 * @date 2020/7/13
 */
public interface MemberQueryApi {

    /**
     * 根据手机号码查询会员信息
     *
     * @param mobile 手机号码
     * @return 会员基本信息
     */
    RspDto<MemberRspDto> selectMemberByMobile(String mobile);

}
