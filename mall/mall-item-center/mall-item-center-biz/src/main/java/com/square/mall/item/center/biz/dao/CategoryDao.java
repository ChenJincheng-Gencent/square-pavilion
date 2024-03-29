package com.square.mall.item.center.biz.dao;

import com.square.mall.item.center.biz.eo.CategoryEo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 分类Dao
 *
 * @author Gencent
 * @date 2020/7/28
 */
public interface CategoryDao {

    /**
     * 插入分类
     *
     * @param categoryEo 分类
     * @return 是否成功，1成功，0失败
     */
    int insertCategory(CategoryEo categoryEo);

    /**
     * 更新分类
     *
     * @param categoryEo 分类
     * @return 是否成功，1成功，0失败
     */
    int updateCategory(CategoryEo categoryEo);

    /**
     * 删除分类
     *
     * @param id 数据库ID
     * @return 是否成功，1成功，0失败
     */
    int deleteCategory(@Param("id") Long id);


    /**
     * 根据上级ID查询分类列表
     *
     * @param parentId 上级ID
     * @return 分类列表
     */
    List<CategoryEo> selectCategoryByParentId(@Param("parentId") Long parentId);

    /**
     * 根据ID查询分类
     *
     * @param id ID
     * @return 分类
     */
    CategoryEo selectCategoryById(@Param("id") Long id);

    /**
     * 条件查询分类列表
     *
     * @param categoryEo 查询条件
     * @return 分类列表
     */
    List<CategoryEo> selectCategoryByCondition(CategoryEo categoryEo);

    /**
     * 批量删除分类
     *
     * @param ids ID数组
     * @return 响应
     */
    int batchDeleteCategory(Long[] ids);

}
