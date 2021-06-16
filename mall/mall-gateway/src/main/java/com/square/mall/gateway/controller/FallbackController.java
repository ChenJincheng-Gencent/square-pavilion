package com.square.mall.gateway.controller;



import com.square.mall.common.dto.CommonRes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 熔断降级返回的接口
 *
 * @author Gencent
 * @date 2021/6/8
 */
@RestController
public class FallbackController {

    @GetMapping("/fallbackA")
    public CommonRes<Void> fallbackA() {
        return CommonRes.FAILURE;
    }

}
