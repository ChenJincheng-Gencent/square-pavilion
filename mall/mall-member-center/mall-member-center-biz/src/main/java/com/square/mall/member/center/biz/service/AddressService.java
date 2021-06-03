package com.square.mall.member.center.biz.service;

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
    List<AddressDto> selectAddressByMemberId(Long memberId);

    /**
     * 插入收货地址
     *
     * @param addressDto 收货地址
     * @return 是否成功，1成功，0失败
     */
    int insertAddress(AddressDto addressDto);

    /**
     * 根据ID更新会员信息
     *
     * @param addressDto 收货地址
     * @return 是否成功，1成功，0失败
     */
    int updateAddress(AddressDto addressDto);

    /**
     * 删除地址
     *
     * @param id 数据库ID
     * @return 是否成功，1成功，0失败
     */
    int deleteAddress(Long id);

    /**
     * 根据ID查询收货地址
     *
     * @param id ID
     * @return 收货地址
     */
    AddressDto selectAddressById(Long id);

}
