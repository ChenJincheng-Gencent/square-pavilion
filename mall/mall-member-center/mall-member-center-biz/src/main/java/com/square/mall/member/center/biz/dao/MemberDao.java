package com.square.mall.member.center.biz.dao;

import com.square.mall.member.center.biz.eo.MemberEo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 会员信息DAO
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
     * @return 会员信息
     */
    MemberEo selectMemberByMobile(@Param("mobile") String mobile);

    /**
     * 插入会员信息
     *
     * @param memberEo 会员信息
     * @return 是否成功，1成功，0失败
     */
    int insertMember(MemberEo memberEo);

    /**
     * 根据手机号码更新会员信息
     *
     * @param memberEo 会员信息
     * @return 是否成功，1成功，0失败
     */
    int updateMemberByMobile(MemberEo memberEo);

}
