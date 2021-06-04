package com.square.mall.member.center.biz.controller;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.member.center.api.AddressApi;
import com.square.mall.member.center.api.dto.AddressDto;
import com.square.mall.member.center.biz.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 收货地址Controller
 *
 * @author Gencent
 * @date 2020/11/11
 */
@RestController
@RequestMapping("/address")
@Api(tags = "收货地址")
public class AddressController implements AddressApi {

    @Resource
    private AddressService addressService;

    /**
     * 插入收货地址
     *
     * @param addressDto 收货地址
     * @return 数据库ID
     */
    @Override
    @PostMapping("/insertAddress")
    @ApiOperation("插入收货地址")
    public CommonRes<Long> insertAddress(@RequestBody AddressDto addressDto) {
        addressService.insertAddress(addressDto);
        return new CommonRes<>(addressDto.getId());
    }

    /**
     * 更新收货地址
     *
     * @param addressDto 收货地址
     * @return 响应
     */
    @Override
    @PutMapping("/updateAddress")
    @ApiOperation("更新收货地址")
    public CommonRes<Void> updateAddress(@RequestBody AddressDto addressDto) {
        addressService.updateAddress(addressDto);
        return CommonRes.SUCCESS;
    }

    /**
     * 删除收货地址
     *
     * @param id 数据库ID
     * @return 响应
     */
    @Override
    @DeleteMapping("/deleteAddress")
    @ApiOperation("删除收货地址")
    public CommonRes<Void> deleteAddress(@RequestParam("id") Long id) {
        addressService.deleteAddress(id);
        return CommonRes.SUCCESS;
    }

    /**
     * 根据会员ID获取收货地址列表
     *
     * @param memberId 会员ID
     * @return 收货地址列表
     */
    @Override
    @GetMapping("/selectAddressByMemberId")
    @ApiOperation("根据会员ID获取收货地址列表")
    public CommonRes<List<AddressDto>> selectAddressByMemberId(@RequestParam("memberId") Long memberId) {
        return new CommonRes<>(addressService.selectAddressByMemberId(memberId));
    }
}
