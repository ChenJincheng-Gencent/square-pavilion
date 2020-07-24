package com.square.mall.member.center.service.apiimpl;

import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.CommonConstant;
import com.square.mall.common.util.ErrorCode;
import com.square.mall.member.center.api.MemberApi;
import com.square.mall.member.center.api.dto.MemberDto;
import com.square.mall.member.center.service.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 *  会员API实现类
 *
 * @author Gencent
 * @date 2019/8/19
 */
@Slf4j
@Service
public class MemberApiImpl implements MemberApi {

    @Resource
    private MemberService memberService;

    @Override
    public RspDto<Long> insertMember(MemberDto memberDto) {
        int success = memberService.insertMember(memberDto);
        if (CommonConstant.DATABASE_OPT_SUCCESS == success) {
            return new RspDto<>(memberDto.getId());
        }
        return new RspDto<>(ErrorCode.ME_CEN_DATABASE_OPT_FAILED);
    }

    @Override
    public RspDto updateMemberByMobile(MemberDto memberDto) {
        int success = memberService.updateMemberByMobile(memberDto);
        if (CommonConstant.DATABASE_OPT_SUCCESS == success) {
            return RspDto.SUCCESS;
        }
        return new RspDto<>(ErrorCode.ME_CEN_DATABASE_OPT_FAILED);
    }

}
