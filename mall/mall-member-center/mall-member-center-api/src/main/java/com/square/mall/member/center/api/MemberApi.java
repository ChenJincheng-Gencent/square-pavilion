package com.square.mall.member.center.api;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.center.api.dto.response.MemberRsp;

/**
 * 会员基本信息RPC接口
 *
 * @author Gencent
 * @date 2019/8/26
 */
public interface MemberApi {

    /**
     * 根据手机号码查询会员信息
     *
     * @param mobile 手机号码
     * @return 会员基本信息
     */
    RspDto<MemberRsp> findMemberByMobile(String mobile);

}
