package com.square.mall.member.center.service.service.impl;

import com.square.mall.member.center.api.dto.response.MemberRsp;
import com.square.mall.member.center.service.dao.MemberDao;
import com.square.mall.member.center.service.eo.MemberEo;
import com.square.mall.member.center.service.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *  会员基本信息Service实现类
 *
 * @author Gencent
 * @date 2019/8/19
 */

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberDao memberDao;

    @Override
    public MemberRsp findMemberByMobile(String mobile) {

        if (StringUtils.isEmpty(mobile)) {
            log.error("mobile is empty.");
            return null;
        }
        MemberEo memberEo = memberDao.findMemberByMobile(mobile);
        if (null == memberEo) {
            log.error("memberEo is null.");
            return null;
        }

        MemberRsp memberRsp = new MemberRsp();
        BeanUtils.copyProperties(memberEo, memberRsp);
        return memberRsp;

    }
}
