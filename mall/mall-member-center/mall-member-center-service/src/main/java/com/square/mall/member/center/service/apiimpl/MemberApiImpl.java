package com.square.mall.member.center.service.apiimpl;

import com.square.mall.member.center.api.MemberApi;
import com.square.mall.member.center.api.dto.response.MemberRsp;
import com.square.mall.member.center.service.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 *  会员基本信息RPC接口实现类
 *
 * @author Gencent
 * @date 2019/8/19
 */
@Slf4j
public class MemberApiImpl implements MemberApi {

    @Resource
    private MemberService memberService;

    @Override
    public MemberRsp findMemberByMobile(String mobile) {

        if (StringUtils.isEmpty(mobile)) {
            log.error("mobile is empty.");
            return null;
        }

        //TODO 需要将返回对象封装到ResponseDto里
        return memberService.findMemberByMobile(mobile);
    }
}
