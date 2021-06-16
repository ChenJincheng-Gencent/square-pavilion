package com.square.mall.gateway.controller;



import com.square.mall.common.dto.CommonRes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @GetMapping("/fallbackA")
    public CommonRes<Void> fallbackA() {
        return CommonRes.FAILURE;
    }

}
