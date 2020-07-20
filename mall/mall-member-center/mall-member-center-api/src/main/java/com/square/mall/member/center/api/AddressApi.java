package com.square.mall.member.center.api;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.center.api.dto.AddressDto;

/**
 * 收货地址API
 *
 * @author Gencent
 * @date 2020/7/20
 */
public interface AddressApi {

    /**
     * 插入收货地址
     *
     * @param addressDto 收货地址
     * @return 数据库ID
     */
    RspDto<Long> insertAddress(AddressDto addressDto);

    /**
     * 根据ID更新会员信息
     *
     * @param addressDto 收货地址
     * @return 响应
     */
    RspDto updateAddress(AddressDto addressDto);

}
