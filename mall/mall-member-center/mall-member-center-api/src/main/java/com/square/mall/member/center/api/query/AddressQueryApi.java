package com.square.mall.member.center.api.query;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.center.api.dto.AddressDto;

import java.util.List;

/**
 * 收货地址查询API
 * @author Gencent
 * @date 2020/7/20
 */
public interface AddressQueryApi {

    /**
     * 根据会员ID查询收货地址列表
     *
     * @param memberId 会员ID
     * @return 收货地址列表
     */
    RspDto<List<AddressDto>> selectAddressByMemberId(Long memberId);

}
