package com.square.mall.member.center.api;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.member.center.api.dto.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收货地址API
 *
 * @author Gencent
 * @date 2020/7/20
 */
@FeignClient(name = "mall-member-center")
public interface AddressApi {

    /**
     * 插入收货地址
     *
     * @param addressDto 收货地址
     * @return 数据库ID
     */
    @PostMapping("/address")
    CommonRes<Long> insertAddress(@RequestBody AddressDto addressDto);

    /**
     * 根据ID更新会员信息
     *
     * @param addressDto 收货地址
     * @return 响应
     */
    @PutMapping("/address")
    CommonRes<Void> updateAddress(@RequestBody AddressDto addressDto);

    /**
     * 删除地址
     *
     * @param id 数据库ID
     * @return 响应
     */
    @DeleteMapping("/address")
    CommonRes<Void> deleteAddress(@RequestParam("id") Long id);

    /**
     * 根据会员ID查询收货地址列表
     *
     * @param memberId 会员ID
     * @return 收货地址列表
     */
    @GetMapping("/address/list")
    CommonRes<List<AddressDto>> selectAddressByMemberId(@RequestParam("memberId") Long memberId);

}
