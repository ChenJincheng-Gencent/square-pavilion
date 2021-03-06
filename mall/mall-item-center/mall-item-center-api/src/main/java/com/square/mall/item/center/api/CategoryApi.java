package com.square.mall.item.center.api;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;
import com.square.mall.item.center.api.dto.CategoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类API
 *
 * @author Gencent
 * @date 2020/8/11
 */
@FeignClient(contextId = "item-category", name="mall-item-center")
public interface CategoryApi {

    /**
     * 插入分类
     *
     * @param categoryDto 分类
     * @return 数据库ID
     */
    @PostMapping("/category")
    CommonRes<Long> insertCategory(@RequestBody CategoryDto categoryDto);

    /**
     * 更新分类
     *
     * @param categoryDto 分类
     * @return 响应
     */
    @PutMapping("/category")
    CommonRes<Void> updateCategory(@RequestBody CategoryDto categoryDto);

    /**
     * 批量删除分类
     *
     * @param ids ID数组
     * @return 响应
     */
    @DeleteMapping("/category/batch/ids")
    CommonRes<Void> batchDeleteCategory(@RequestParam("ids") Long[] ids);

    /**
     * 根据上级ID查询分类列表
     *
     * @param parentId 上级ID
     * @return 分类列表
     */
    @GetMapping("/category/list/parent-id")
    CommonRes<List<CategoryDto>> selectCategoryByParentId(@RequestParam("parentId") Long parentId);

    /**
     * 根据ID查询分类
     *
     * @param id ID
     * @return 分类
     */
    @GetMapping("/category/id")
    CommonRes<CategoryDto> selectCategoryById(@RequestParam("id") Long id);

    /**
     * 分页条件查询分类列表
     *
     * @param categoryDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 分类列表
     */
    @PostMapping("/category/list/page/condition")
    CommonPageRes<List<CategoryDto>> selectPageCategoryByCondition(@RequestBody CategoryDto categoryDto, @RequestParam("pageNum") Integer pageNum,
                                                                   @RequestParam("pageSize") Integer pageSize);
}
