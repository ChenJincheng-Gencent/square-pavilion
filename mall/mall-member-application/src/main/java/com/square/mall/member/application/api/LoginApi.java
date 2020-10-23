package com.square.mall.member.application.api;

import com.square.mall.common.dto.RspDto;
import com.square.mall.member.center.api.dto.LoginDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 登录信息API
 *
 * @author Gencent
 * @date 2020/7/17
 */
@FeignClient(name = "mall-member-center")
public interface LoginApi {

    /**
     * 插入登录信息
     *
     * @param loginDto 登录信息
     * @return 数据库ID
     */
    @PostMapping("/login")
    RspDto<Long> insertLogin(@RequestBody LoginDto loginDto);

}
