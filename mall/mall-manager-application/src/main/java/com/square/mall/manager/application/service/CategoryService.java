package com.square.mall.manager.application.service;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;
import com.square.mall.item.center.api.dto.CategoryDto;

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
    CommonRes<List<CategoryDto>> selectCategoryByParentId(Long parentId);

    /**
     * 根据ID查询分类
     *
     * @param id ID
     * @return 分类
     */
    CommonRes<CategoryDto> selectCategoryById(Long id);

    /**
     * 插入分类
     *
     * @param categoryDto 分类
     * @return 数据库ID
     */
    CommonRes<Long> insertCategory(CategoryDto categoryDto);

    /**
     * 更新分类
     *
     * @param categoryDto 分类
     * @return 响应
     */
    CommonRes<Void> updateCategory(CategoryDto categoryDto);

    /**
     * 分页条件查询分类列表
     *
     * @param categoryDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 分类列表
     */
    CommonPageRes<List<CategoryDto>> selectPageCategoryByCondition(CategoryDto categoryDto, Integer pageNum, Integer pageSize);

    /**
     * 删除分类列表
     *
     * @param ids ID数组
     * @return 响应
     */
    CommonRes<Void> batchDeleteCategory(Long[] ids);

}
