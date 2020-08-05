package com.square.mall.item.center.api;

import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.TemplateGroupDto;

/**
 * 模板API
 *
 * @author Gencent
 * @date 2020/7/31
 */
public interface TemplateApi {

    /**
     * 插入模板组合
     *
     * @param templateGroupDto 模板组合
     * @return 数据库ID
     */
    RspDto<Long> insertTemplateGroup(TemplateGroupDto templateGroupDto);

    /**
     * 更新模板组合
     *
     * @param templateGroupDto 模板组合
     * @return 响应
     */
    RspDto updateTemplateGroup(TemplateGroupDto templateGroupDto);

    /**
     * 批量删除模板组合
     *
     * @param ids 模板ID数组
     * @return 响应
     */
    RspDto batchDeleteTemplateGroup(Long[] ids);

}
