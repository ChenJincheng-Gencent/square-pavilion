package com.square.mall.member.center.service.apiimpl.query;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.center.api.dto.AddressDto;
import com.square.mall.member.center.api.query.AddressQueryApi;
import com.square.mall.member.center.service.service.AddressService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收货地址查询API实现类
 *
 * @author Gencent
 * @date 2020/7/20
 */

public class AddressQueryApiImpl implements AddressQueryApi {

    @Resource
    private AddressService addressService;

    @Override
    public RspDto<List<AddressDto>> selectAddressByMemberId(Long memberId) {
        return new RspDto<>(addressService.selectAddressByMemberId(memberId));
    }
}
