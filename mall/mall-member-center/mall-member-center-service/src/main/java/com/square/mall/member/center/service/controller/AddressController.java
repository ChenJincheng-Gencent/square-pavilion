package com.square.mall.member.center.service.controller;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.member.center.api.dto.AddressDto;
import com.square.mall.member.center.service.service.AddressService;
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
public class AddressController {

    @Resource
    private AddressService addressService;

    /**
     * 插入收货地址
     *
     * @param addressDto 收货地址
     * @return 数据库ID
     */
    @PostMapping("")
    public CommonRes<Long> insertAddress(@RequestBody AddressDto addressDto) {
        int success = addressService.insertAddress(addressDto);
        return new CommonRes<>(addressDto.getId());
    }

    /**
     * 更新收货地址
     *
     * @param addressDto 收货地址
     * @return 响应
     */
    @PutMapping("")
    public CommonRes<Void> updateAddress(@RequestBody AddressDto addressDto) {
        int success = addressService.updateAddress(addressDto);
        return CommonRes.SUCCESS;
    }

    /**
     * 删除收货地址
     *
     * @param id 数据库ID
     * @return 响应
     */
    @DeleteMapping("")
    public CommonRes<Void> deleteAddress(@RequestParam("id") Long id) {
        int success = addressService.deleteAddress(id);
        return CommonRes.SUCCESS;
    }

    /**
     * 根据会员ID获取收货地址列表
     *
     * @param memberId 会员ID
     * @return 收货地址列表
     */
    @GetMapping("/list")
    public CommonRes<List<AddressDto>> selectAddressByMemberId(@RequestParam("memberId") Long memberId) {
        return new CommonRes<>(addressService.selectAddressByMemberId(memberId));
    }
}
