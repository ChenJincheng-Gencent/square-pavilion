package com.square.mall.member.application.service.impl;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.member.application.service.AddressService;
import com.square.mall.member.center.api.AddressApi;
import com.square.mall.member.center.api.dto.AddressDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收货地址Service实现类
 *
 * @author Gencent
 * @date 2020/7/20
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressApi addressApi;

    @Override
    public CommonRes<List<AddressDto>> selectAddressByMemberId(Long memberId) {
        return addressApi.selectAddressByMemberId(memberId);
    }

    @Override
    public CommonRes<Long> insertAddress(AddressDto addressDto) {
        return addressApi.insertAddress(addressDto);
    }

    @Override
    public CommonRes<Void> updateAddress(AddressDto addressDto) {
        return addressApi.updateAddress(addressDto);
    }

    @Override
    public CommonRes<Void> deleteAddress(Long id) {
        return addressApi.deleteAddress(id);
    }
}
