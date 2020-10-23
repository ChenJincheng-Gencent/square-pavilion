package com.square.mall.member.center.service.apiimpl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.DatabaseUtil;
import com.square.mall.common.util.ModuleConstant;
import com.square.mall.member.center.api.AddressApi;
import com.square.mall.member.center.api.dto.AddressDto;
import com.square.mall.member.center.service.service.AddressService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 收货地址API实现类
 *
 * @author Gencent
 * @date 2020/7/20
 */

@Transactional(rollbackFor = Exception.class)
public class AddressApiImpl implements AddressApi {

    @Resource
    private AddressService addressService;

    @Override
    public RspDto<Long> insertAddress(AddressDto addressDto) {
        int success = addressService.insertAddress(addressDto);
        return DatabaseUtil.getResult(success, addressDto.getId(), ModuleConstant.MEMBER_CENTER);
    }

    @Override
    public RspDto updateAddress(AddressDto addressDto) {
        int success = addressService.updateAddress(addressDto);
        return DatabaseUtil.getResult(success, ModuleConstant.MEMBER_CENTER);
    }

    @Override
    public RspDto deleteAddress(Long id) {
        int success = addressService.deleteAddress(id);
        return DatabaseUtil.getResult(success, ModuleConstant.MEMBER_CENTER);
    }

}
