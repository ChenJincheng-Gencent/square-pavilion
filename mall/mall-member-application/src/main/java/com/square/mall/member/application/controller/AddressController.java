package com.square.mall.member.application.controller;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.application.service.AddressService;
import com.square.mall.member.application.vo.ModAddressVo;
import com.square.mall.member.center.api.dto.AddressDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 收货地址Controller
 *
 * @author Gencent
 * @date 2020/7/20
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/member/v1")
@Slf4j
@Validated
@Api(tags = "收货地址REST API")
public class AddressController {

    @Resource
    private AddressService addressService;

    /**
     * 根据会员ID获取收货地址列表
     *
     * @param memberId 会员ID
     * @return 收货地址列表
     */
    @GetMapping("/address/list")
    @ResponseBody
    @ApiOperation(value = "根据会员ID获取收货地址列表")
    @ApiImplicitParam(name = "memberId", value = "会员ID", paramType = "query", dataTypeClass = Long.class,
        required = true, example = "11")
    public RspDto selectAddressByMemberId(@RequestParam("memberId") @NotNull(message = "会员ID不能为空") Long memberId) {

        RspDto<List<AddressDto>> addressDtoList = addressService.selectAddressByMemberId(memberId);
        log.info("AddressDtoList: {}, memberId: {}", addressDtoList, memberId);

        return addressDtoList;

    }

    /**
     * 插入收货地址
     *
     * @param addressDto 收货地址
     * @return 数据库ID
     */
    @PostMapping("/address")
    @ResponseBody
    @ApiOperation(value = "插入收货地址")
    public RspDto insertAddress(@RequestBody @Valid AddressDto addressDto) {
        RspDto<Long> id = addressService.insertAddress(addressDto);
        log.info("id: {}, addressDto: {}", id.getData(), addressDto);
        return id;
    }

    /**
     * 更新收货地址
     *
     * @param modAddressVo 收货地址
     * @return 响应
     */
    @PutMapping("/address")
    @ResponseBody
    @ApiOperation(value = "更新收货地址")
    public RspDto updateAddress(@RequestBody @Valid ModAddressVo modAddressVo) {
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties(modAddressVo, addressDto);
        return addressService.updateAddress(addressDto);
    }

    /**
     * 删除收货地址
     *
     * @param id 数据库ID
     * @return 响应
     */
    @DeleteMapping("/address")
    @ResponseBody
    @ApiOperation(value = "删除收货地址")
    public RspDto deleteAddress(@RequestBody @RequestParam("id") @NotNull(message = "ID不能为空") Long id) {
        return addressService.deleteAddress(id);
    }

}
