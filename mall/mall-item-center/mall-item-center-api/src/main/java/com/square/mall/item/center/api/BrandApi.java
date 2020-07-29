package com.square.mall.item.center.api;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.BrandDto;

/**
 * 品牌API
 *
 * @author Gencent
 * @date 2020/7/24
 */
public interface BrandApi {

    /**
     * 插入品牌
     *
     * @param brandDto 品牌
     * @return 数据库ID
     */
    RspDto<Long> insertBrand(BrandDto brandDto);

    /**
     * 更新品牌
     *
     * @param brandDto 品牌
     * @return 响应
     */
    RspDto updateBrand(BrandDto brandDto);

    /**
     * 删除品牌
     *
     * @param id 数据库ID
     * @return 响应
     */
    RspDto deleteBrand(Long id);
}
