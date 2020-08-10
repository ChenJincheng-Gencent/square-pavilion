package com.square.mall.item.center.api.query;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.CategoryDto;

import java.util.List;

/**
 * 分类查询API
 *
 * @author Gencent
 * @date 2020/8/10
 */
public interface CategoryQueryApi {

    /**
     * 根据上级ID查询分类列表
     *
     * @param parentId 上级ID
     * @return 分类列表
     */
    RspDto<List<CategoryDto>> selectCategoryByParentId(Long parentId);
}
