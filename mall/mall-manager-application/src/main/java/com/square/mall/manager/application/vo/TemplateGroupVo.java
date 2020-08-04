package com.square.mall.manager.application.vo;

import com.square.mall.item.center.api.dto.ExtraAttributesDto;
import com.square.mall.item.center.api.dto.TemplateDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 模板组合VO
 *
 * @author Gencent
 * @date 2020/7/31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TemplateGroupVo", description = "模板组合VO")
public class TemplateGroupVo {

    /**
     * 模板
     */
    private TemplateDto templateDto;

    /**
     * 品牌列表
     */
    List<Select2Vo> brandDtoList;

    /**
     * 规格列表
     */
    List<Select2Vo> specificationDtoList;

    /**
     * 扩展属性列表
     */
    private List<ExtraAttributesDto> extraAttributesDtoList;

}
