package com.square.mall.item.center.biz.service;

import com.square.mall.common.dto.CommonPageRes;
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
    List<CategoryDto> selectCategoryByParentId(Long parentId);

    /**
     * 根据ID查询分类
     *
     * @param id ID
     * @return 分类
     */
    CategoryDto selectCategoryById(Long id);

    /**
     * 插入分类
     *
     * @param categoryDto 分类
     * @return 是否成功，1成功，0失败
     */
    int insertCategory(CategoryDto categoryDto);

    /**
     * 更新分类
     *
     * @param categoryDto 分类
     * @return 是否成功，1成功，0失败
     */
    int updateCategory(CategoryDto categoryDto);

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
     * 批量删除分类
     *
     * @param ids ID数组
     * @return 响应
     */
    int batchDeleteCategory(Long[] ids);

}
