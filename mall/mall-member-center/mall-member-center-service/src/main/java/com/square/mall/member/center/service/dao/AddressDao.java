package com.square.mall.member.center.service.dao;

import com.square.mall.member.center.api.dto.AddressDto;
import com.square.mall.member.center.service.eo.AddressEo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 收货地址Dao层
 *
 * @author Gencent
 * @date 2020/7/20
 */
@Mapper
public interface AddressDao {

    /**
     * 根据会员ID查询收货地址列表
     *
     * @param memberId 会员ID
     * @return 收货地址列表
     */
    List<AddressEo> selectAddressByMemberId(Long memberId);

    /**
     * 插入收货地址
     *
     * @param addressEo 收货地址
     * @return 是否成功，1成功，0失败
     */
    int insertAddress(AddressEo addressEo);

    /**
     * 根据ID更新会员信息
     *
     * @param addressEo 收货地址
     * @return 是否成功，1成功，0失败
     */
    int updateAddress(AddressEo addressEo);

    /**
     * 删除地址
     *
     * @param id 数据库ID
     * @return 是否成功，1成功，0失败
     */
    int deleteAddress(@Param("id") Long id);

    /**
     * 根据ID查询收货地址
     *
     * @param id ID
     * @return 收货地址
     */
    AddressEo selectAddressById(@Param("id") Long id);

}
