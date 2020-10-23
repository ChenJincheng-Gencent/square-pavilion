package com.square.mall.member.center.service.controller;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.DatabaseUtil;
import com.square.mall.common.util.ModuleConstant;
import com.square.mall.member.center.api.dto.AddressDto;
import com.square.mall.member.center.service.service.AddressService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public RspDto<Long> insertAddress(@RequestBody AddressDto addressDto) {
        int success = addressService.insertAddress(addressDto);
        return DatabaseUtil.getResult(success, addressDto.getId(), ModuleConstant.MEMBER_CENTER);
    }

    /**
     * 更新收货地址
     *
     * @param addressDto 收货地址
     * @return 响应
     */
    @PutMapping("")
    public RspDto updateAddress(@RequestBody AddressDto addressDto) {
        int success = addressService.updateAddress(addressDto);
        return DatabaseUtil.getResult(success, ModuleConstant.MEMBER_CENTER);
    }

    /**
     * 删除收货地址
     *
     * @param id 数据库ID
     * @return 响应
     */
    @DeleteMapping("")
    public RspDto deleteAddress(@RequestParam("id") Long id) {
        int success = addressService.deleteAddress(id);
        return DatabaseUtil.getResult(success, ModuleConstant.MEMBER_CENTER);
    }

    /**
     * 根据会员ID获取收货地址列表
     *
     * @param memberId 会员ID
     * @return 收货地址列表
     */
    @GetMapping("/list")
    public RspDto<List<AddressDto>> selectAddressByMemberId(@RequestParam("memberId") Long memberId) {
        return new RspDto<>(addressService.selectAddressByMemberId(memberId));
    }
}
