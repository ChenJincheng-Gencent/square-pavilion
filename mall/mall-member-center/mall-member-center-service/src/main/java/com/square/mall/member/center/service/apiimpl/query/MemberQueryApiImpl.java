package com.square.mall.member.center.service.apiimpl.query;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.center.api.dto.response.MemberRspDto;
import com.square.mall.member.center.api.query.MemberQueryApi;
import com.square.mall.member.center.service.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * 会员查询API实现类
 * @author Gencent
 * @date 2020/7/13
 *
 */
@Service
@Slf4j
public class MemberQueryApiImpl implements MemberQueryApi {

    @Resource
    private MemberService memberService;

    @Override
    public RspDto<MemberRspDto> selectMemberByMobile(String mobile) {

        if (StringUtils.isEmpty(mobile)) {
            log.error("mobile is empty.");
            return null;
        }

        return new RspDto<>(memberService.selectMemberByMobile(mobile));
    }


}
