package com.square.mall.item.center.service.service;

import com.square.mall.item.center.api.dto.CategoryDto;
import com.square.mall.item.center.service.eo.CategoryEo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分类Service
 *
 * @author Gencent
 * @date 2020/8/10
 */
public interface CategoryService {

    /**
     * 根据上级ID查询分类列表
     *
     * @param parentId 上级ID
     * @return 分类列表
     */
    List<CategoryDto> selectCategoryByParentId(Long parentId);

    /**
     * 根据ID查询分类
     *
     * @param id ID
     * @return 分类
     */
    CategoryDto selectCategoryById(Long id);
}
