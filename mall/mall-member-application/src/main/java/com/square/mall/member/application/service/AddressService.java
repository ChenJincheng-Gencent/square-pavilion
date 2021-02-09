package com.square.mall.member.application.service;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.member.center.api.dto.AddressDto;

import java.util.List;

/**
 * 收货地址Service
 *
 * @author Gencent
 * @date 2020/7/20
 */
public interface AddressService {

    /**
     * 根据会员ID查询收货地址列表
     *
     * @param memberId 会员ID
     * @return 收货地址列表
     */
    CommonRes<List<AddressDto>> selectAddressByMemberId(Long memberId);

    /**
     * 插入收货地址
     *
     * @param addressDto 收货地址
     * @return 数据库ID
     */
    CommonRes<Long> insertAddress(AddressDto addressDto);

    /**
     * 根据ID更新会员信息
     *
     * @param addressDto 收货地址
     * @return 响应
     */
    CommonRes<Void> updateAddress(AddressDto addressDto);

    /**
     * 删除收货地址
     *
     * @param id 数据库ID
     * @return 响应
     */
    CommonRes<Void> deleteAddress(Long id);
}
