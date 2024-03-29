package com.square.mall.member.center.biz.service.impl;

import com.square.mall.member.center.api.dto.LoginDto;
import com.square.mall.member.center.biz.dao.LoginDao;
import com.square.mall.member.center.biz.eo.LoginEo;
import com.square.mall.member.center.biz.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 登录Service实现类
 *
 * @author Gencent
 * @date 2020/7/17
 */

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginDao loginDao;

    @Override
    public Long insertLogin(LoginDto loginDto) {
        if (null == loginDto) {
            log.error("loginDto is null.");
            return 0L;
        }

        LoginEo loginEo = new LoginEo();
        BeanUtils.copyProperties(loginDto, loginEo);
        loginDao.insertLogin(loginEo);
        return loginEo.getId();
    }
}
