package com.square.mall.item.center.api.query;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.CategoryDto;

/**
 * 分类查询API
 *
 * @author Gencent
 * @date 2020/8/10
 */
public interface CategoryQueryApi {

    /**
     * 根据上级ID查询分类
     *
     * @param parentId 上级ID
     * @return 分类
     */
    RspDto<CategoryDto> selectCategoryByParentId(Long parentId);
}
