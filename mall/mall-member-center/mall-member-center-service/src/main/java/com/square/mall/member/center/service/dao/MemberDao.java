package com.square.mall.member.center.service.dao;

import com.square.mall.member.center.service.eo.MemberEo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员基本信息DAO层
 *
 * @author Gencent
 * @date 2019/8/20
 */
@Mapper
public interface MemberDao {

    /**
     * 根据手机号码查询会员信息
     *
     * @param phone 手机号码
     * @return 会员基本信息
     */
    MemberEo findMemberByPhone(String phone);

}
