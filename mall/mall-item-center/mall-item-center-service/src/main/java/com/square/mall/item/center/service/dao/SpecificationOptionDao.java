package com.square.mall.item.center.service.dao;

import com.square.mall.item.center.service.eo.SpecificationEo;
import com.square.mall.item.center.service.eo.SpecificationOptionEo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 规格选项Dao
 *
 * @author Gencent
 * @date 2020/7/27
 */
public interface SpecificationOptionDao {

    /**
     * 插入规格选项
     *
     * @param specificationOptionEo 规格选项
     * @return 是否成功，1成功，0失败
     */
    int insertSpecificationOption(SpecificationOptionEo specificationOptionEo);

    /**
     * 更新规格选项
     *
     * @param specificationOptionEo 规格选项
     * @return 是否成功，1成功，0失败
     */
    int updateSpecificationOption(SpecificationOptionEo specificationOptionEo);

    /**
     * 删除规格选项
     *
     * @param id 数据库ID
     * @return 是否成功，1成功，0失败
     */
    int deleteSpecificationOption(@Param("id") Long id);

    /**
     * 条件查询规格选项列表
     * @param specificationOptionEo 查询条件
     * @return 规格选项列表
     */
    List<SpecificationOptionEo> selectSpecificationOptionByCondition(SpecificationOptionEo specificationOptionEo);

    /**
     * 根据名称查询规格选项
     *
     * @param name 名称
     * @return 规格选项
     */
    SpecificationOptionEo selectSpecificationOptionByName(@Param("name") String name);

    /**
     * 根据ID查询规格选项
     *
     * @param id ID
     * @return 规格选项
     */
    SpecificationOptionEo selectSpecificationOptionById(@Param("id") Long id);

}
