package com.square.mall.member.center.service.dao;

import com.square.mall.member.center.service.eo.MemberEo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * @param mobile 手机号码
     * @return 会员基本信息
     */
    MemberEo findMemberByMobile(@Param("mobile") String mobile);

    /**
     * 插入会员信息
     *
     * @param memberEo 会员信息
     * @return 数据库ID
     */
    Long insertMember(MemberEo memberEo);

}
