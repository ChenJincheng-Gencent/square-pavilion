package com.square.mall.item.center.api;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.CategoryDto;

/**
 * 分类API
 *
 * @author Gencent
 * @date 2020/8/11
 */
public interface CategoryApi {

    /**
     * 插入分类
     *
     * @param categoryDto 分类
     * @return 数据库ID
     */
    RspDto<Long> insertCategory(CategoryDto categoryDto);

    /**
     * 更新分类
     *
     * @param categoryDto 分类
     * @return 响应
     */
    RspDto updateCategory(CategoryDto categoryDto);

    /**
     * 批量删除分类
     *
     * @param ids ID数组
     * @return 响应
     */
    RspDto batchDeleteCategory(Long[] ids);
}
