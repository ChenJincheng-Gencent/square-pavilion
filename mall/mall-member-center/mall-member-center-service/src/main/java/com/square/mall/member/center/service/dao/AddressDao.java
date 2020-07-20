package com.square.mall.member.center.service.dao;

import com.square.mall.member.center.service.eo.AddressEo;
import org.apache.ibatis.annotations.Mapper;

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
     *
     */
    void insertAddress(AddressEo addressEo);

    /**
     * 根据ID更新会员信息
     *
     * @param addressEo 收货地址
     *
     */
    void updateAddress(AddressEo addressEo);

}
