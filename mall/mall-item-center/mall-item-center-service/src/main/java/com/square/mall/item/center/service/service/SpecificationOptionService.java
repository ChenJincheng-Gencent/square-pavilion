package com.square.mall.item.center.service.service;

import com.square.mall.item.center.api.dto.SpecificationOptionDto;
import com.square.mall.item.center.service.eo.SpecificationOptionEo;

import java.util.List;

/**
 * 规格选项Service
 *
 * @author Gencent
 * @date 2020/7/27
 */
public interface SpecificationOptionService {

    /**
     * 插入规格选项
     *
     * @param specificationOptionDto 规格选项
     * @return 操作结果
     */
    int insertSpecificationOption(SpecificationOptionDto specificationOptionDto);

    /**
     * 更新规格选项
     *
     * @param specificationOptionDto 规格选项
     * @return 操作结果
     */
    int updateSpecificationOption(SpecificationOptionDto specificationOptionDto);

    /**
     * 删除规格选项
     *
     * @param id ID
     * @return 操作结果
     */
    int deleteSpecificationOption(Long id);

    /**
     * 批量删除规格选项
     *
     * @param ids ID数组
     * @return 操作结果
     */
    int batchDeleteSpecificationOption(Long[] ids);

    /**
     * 根据规格ID删除规格选项
     *
     * @param specId 规格ID
     * @return 操作结果
     */
    int deleteSpecificationOptionBySpecId(Long specId);

    /**
     * 根据规格ID查询规格选项列表
     *
     * @param specId 规格ID
     * @return 规格选项列表
     */
    List<SpecificationOptionDto> selectSpecificationOptionBySpecId(Long specId);

}
