package com.square.mall.member.application.service.impl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.application.service.AddressService;
import com.square.mall.member.center.api.AddressApi;
import com.square.mall.member.center.api.dto.AddressDto;
import com.square.mall.member.center.api.query.AddressQueryApi;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收货地址Service实现类
 *
 * @author Gencent
 * @date 2020/7/20
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Reference
    private AddressApi addressApi;

    @Reference
    private AddressQueryApi addressQueryApi;

    @Override
    public RspDto<List<AddressDto>> selectAddressByMemberId(Long memberId) {
        return addressQueryApi.selectAddressByMemberId(memberId);
    }

    @Override
    public RspDto<Long> insertAddress(AddressDto addressDto) {
        return addressApi.insertAddress(addressDto);
    }

    @Override
    public RspDto updateAddress(AddressDto addressDto) {
        return addressApi.updateAddress(addressDto);
    }
}
