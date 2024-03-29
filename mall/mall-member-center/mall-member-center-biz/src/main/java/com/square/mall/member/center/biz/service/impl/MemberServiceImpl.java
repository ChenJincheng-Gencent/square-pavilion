package com.square.mall.member.center.biz.service.impl;

import com.square.mall.common.util.StringUtil;
import com.square.mall.member.center.api.dto.MemberDto;
import com.square.mall.member.center.biz.dao.MemberDao;
import com.square.mall.member.center.biz.eo.MemberEo;
import com.square.mall.member.center.biz.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 会员Service实现类
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
    public MemberDto selectMemberByMobile(String mobile) {

        if (StringUtil.isBlank(mobile)) {
            log.error("mobile is blank.");
            return null;
        }
        MemberEo memberEo = memberDao.selectMemberByMobile(mobile);
        if (null == memberEo) {
            log.error("memberEo is null. mobile: {}", mobile);
            return null;
        }

        MemberDto memberDto = new MemberDto();
        BeanUtils.copyProperties(memberEo, memberDto);
        return memberDto;

    }

    @Override
    public int insertMember(MemberDto memberDto) {

        if (null == memberDto || StringUtil.isBlank(memberDto.getMobile())) {
            log.error("memberDto or mobile is null or blank.");
            return 0;
        }

        MemberEo oldMemberEo = memberDao.selectMemberByMobile(memberDto.getMobile());
        if (null != oldMemberEo) {
            log.error("oldMemberEo already exist. mobile: {}", memberDto.getMobile());
            return 0;
        }
        MemberEo memberEo = new MemberEo();
        BeanUtils.copyProperties(memberDto, memberEo);
        int success = memberDao.insertMember(memberEo);
        memberDto.setId(memberEo.getId());
        return success;

    }

    @Override
    public int updateMemberByMobile(MemberDto memberDto) {

        if (null == memberDto || StringUtil.isBlank(memberDto.getMobile())) {
            log.error("memberDto or mobile is null or blank.");
            return 0;
        }

        MemberEo oldMemberEo = memberDao.selectMemberByMobile(memberDto.getMobile());
        if (null == oldMemberEo) {
            log.error("oldMemberEo is null. mobile: {}", memberDto.getMobile());
            return 0;
        }
        MemberEo memberEo = new MemberEo();
        BeanUtils.copyProperties(memberDto, memberEo);
        return memberDao.updateMemberByMobile(memberEo);

    }

}
