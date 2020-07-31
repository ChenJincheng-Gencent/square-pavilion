package com.square.mall.item.center.service.dao;

import com.square.mall.item.center.service.eo.SpecificationEo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 规格dao
 *
 * @author Gencent
 * @date 2020/7/27
 */
public interface SpecificationDao {

    /**
     * 插入规格
     *
     * @param specificationEo 规格
     * @return 是否成功，1成功，0失败
     */
    int insertSpecification(SpecificationEo specificationEo);

    /**
     * 更新规格
     *
     * @param specificationEo 规格
     * @return 是否成功，1成功，0失败
     */
    int updateSpecification(SpecificationEo specificationEo);

    /**
     * 删除规格
     *
     * @param id 数据库ID
     * @return 是否成功，1成功，0失败
     */
    int deleteSpecification(@Param("id") Long id);

    /**
     * 条件查询规格列表
     *
     * @param specificationEo 查询条件
     * @return 规格列表
     */
    List<SpecificationEo> selectSpecificationByCondition(SpecificationEo specificationEo);

    /**
     * 根据名称查询规格
     *
     * @param name 名称
     * @return 规格
     */
    SpecificationEo selectSpecificationByName(@Param("name") String name);

    /**
     * 根据ID查询规格
     *
     * @param id ID
     * @return 规格
     */
    SpecificationEo selectSpecificationById(@Param("id") Long id);

    /**
     * 查询所有规格列表
     *
     * @return 规格列表
     */
    List<SpecificationEo> selectSpecificationAll();

}
