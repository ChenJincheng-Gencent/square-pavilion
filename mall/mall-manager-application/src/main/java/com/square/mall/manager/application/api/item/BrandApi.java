package com.square.mall.manager.application.api.item;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.BrandDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * 品牌API
 *
 * @author Gencent
 * @date 2020/7/24
 */

@FeignClient(name="BrandApi")
@Component
public interface BrandApi {

    /**
     * 插入品牌
     *
     * @param brandDto 品牌
     * @return 数据库ID
     */
    @PostMapping("/brand")
    RspDto<Long> insertBrand(@RequestBody BrandDto brandDto);

    /**
     * 更新品牌
     *
     * @param brandDto 品牌
     * @return 响应
     */
    @PutMapping("/brand")
    RspDto updateBrand(@RequestBody BrandDto brandDto);

    /**
     * 删除品牌
     *
     * @param id 数据库ID
     * @return 响应
     */
    @DeleteMapping("/brand")
    RspDto deleteBrand(@RequestParam("id") Long id);

    /**
     * 批量删除品牌
     *
     * @param ids ID数组
     * @return 响应
     */
    @DeleteMapping("/brand/batch")
    RspDto batchDeleteBrand(@RequestParam("ids") Long[] ids);
}
