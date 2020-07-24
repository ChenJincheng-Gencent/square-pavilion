package com.square.mall.member.center.service.apiimpl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.CommonConstant;
import com.square.mall.common.util.ErrorCode;
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
        int success = addressService.insertAddress(addressDto);
        if (CommonConstant.DATABASE_OPT_SUCCESS == success) {
            return new RspDto<>(addressDto.getId());
        }
        return new RspDto<>(ErrorCode.ME_CEN_DATABASE_OPT_FAILED);
    }

    @Override
    public RspDto updateAddress(AddressDto addressDto) {
        int success = addressService.updateAddress(addressDto);
        if (CommonConstant.DATABASE_OPT_SUCCESS == success) {
            return RspDto.SUCCESS;
        }
        return new RspDto<>(ErrorCode.ME_CEN_DATABASE_OPT_FAILED);
    }

    @Override
    public RspDto deleteAddress(Long id) {
        int success = addressService.deleteAddress(id);
        if (CommonConstant.DATABASE_OPT_SUCCESS == success) {
            return RspDto.SUCCESS;
        }
        return new RspDto<>(ErrorCode.ME_CEN_DATABASE_OPT_FAILED);
    }

}
