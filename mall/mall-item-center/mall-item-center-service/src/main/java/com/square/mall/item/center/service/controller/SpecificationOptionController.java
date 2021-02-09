package com.square.mall.item.center.service.controller;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.item.center.api.dto.SpecificationOptionDto;
import com.square.mall.item.center.service.service.SpecificationOptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规格选项Controller
 *
 * @author Gencent
 * @date 2020/10/26
 */
@RestController
@RequestMapping("/specification/option")
public class SpecificationOptionController {

    @Resource
    private SpecificationOptionService specificationOptionService;

    @GetMapping("/list/spec-id")
    public CommonRes<List<SpecificationOptionDto>> selectSpecificationOptionBySpecId(@RequestParam("specId") Long specId) {
        return new CommonRes<>(specificationOptionService.selectSpecificationOptionBySpecId(specId));
    }
}
