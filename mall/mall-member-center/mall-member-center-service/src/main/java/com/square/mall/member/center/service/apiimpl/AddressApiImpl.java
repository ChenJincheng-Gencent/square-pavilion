package com.square.mall.member.center.service.apiimpl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.center.api.AddressApi;
import com.square.mall.member.center.api.dto.AddressDto;
import com.square.mall.member.center.service.service.AddressService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * 收货地址API实现类
 *
 * @author Gencent
 * @date 2020/7/20
 */
@Service
public class AddressApiImpl implements AddressApi {

    @Resource
    private AddressService addressService;

    @Override
    public RspDto<Long> insertAddress(AddressDto addressDto) {
        return new RspDto<>(addressService.insertAddress(addressDto));
    }

    @Override
    public RspDto updateAddress(AddressDto addressDto) {
        addressService.updateAddress(addressDto);
        return RspDto.SUCCESS;
    }
}
