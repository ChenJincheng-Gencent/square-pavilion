package com.square.mall.item.center.api;

import com.square.mall.common.dto.CommonRes;
import com.square.mall.item.center.api.dto.SpecificationOptionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 规格选项API
 *
 * @author Gencent
 * @date 2020/7/27
 */
@FeignClient(contextId = "item-specification-option", name="mall-item-center")
public interface SpecificationOptionApi {

    /**
     * 根据规格ID查询规格选项列表
     *
     * @param specId 规格ID
     * @return 规格选项列表
     */
    @GetMapping("/specification/option/list/spec-id")
    CommonRes<List<SpecificationOptionDto>> selectSpecificationOptionBySpecId(@RequestParam("specId") Long specId);

}
