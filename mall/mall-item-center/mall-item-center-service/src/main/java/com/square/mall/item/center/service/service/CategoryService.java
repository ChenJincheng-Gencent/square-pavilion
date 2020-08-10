package com.square.mall.item.center.service.service;

import com.square.mall.item.center.api.dto.CategoryDto;

/**
 * 分类Service
 *
 * @author Gencent
 * @date 2020/8/10
 */
public interface CategoryService {

    /**
     * 根据上级ID查询分类
     *
     * @param parentId 上级ID
     * @return 分类
     */
    CategoryDto selectCategoryByParentId(Long parentId);
}
