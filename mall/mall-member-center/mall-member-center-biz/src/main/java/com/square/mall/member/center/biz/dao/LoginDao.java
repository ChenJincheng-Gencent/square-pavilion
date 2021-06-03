package com.square.mall.member.center.biz.dao;

import com.square.mall.member.center.biz.eo.LoginEo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 登录信息DAO
 *
 * @author Gencent
 * @date 2020/7/17
 */
@Mapper
public interface LoginDao {

    /**
     * 插入登录信息
     *
     * @param loginEo 登录信息
     *
     */
    void insertLogin(LoginEo loginEo);

}
