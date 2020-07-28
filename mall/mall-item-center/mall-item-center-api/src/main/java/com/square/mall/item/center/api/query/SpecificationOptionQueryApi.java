package com.square.mall.item.center.api.query;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.SpecificationOptionDto;

import java.util.List;

/**
 * 规格选项查询API
 *
 * @author Gencent
 * @date  2020/7/28
 */
public interface SpecificationOptionQueryApi {

    /**
     * 根据规格ID查询规格选项列表
     *
     * @param specId 规格ID
     * @return 规格选项列表
     */
    RspDto<List<SpecificationOptionDto>> selectSpecificationOptionBySpecId(Long specId);

}
