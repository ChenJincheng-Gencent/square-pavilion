package com.square.mall.item.center.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 模板组合DTO
 *
 * @author Gencent
 * @date 2020/7/31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TemplateGroupDto", description = "模板组合")
public class TemplateGroupDto implements Serializable {

    private static final long serialVersionUID = -4714688796134415775L;

    /**
     * 模板
     */
    private TemplateDto templateDto;

    /**
     * 品牌列表
     */
    private List<BrandDto> brandDtoList;

    /**
     * 规格列表
     */
    private List<SpecificationDto> specificationDtoList;

    /**
     * 扩展属性列表
     */
    private List<ExtraAttributesDto> extraAttributesDtoList;

}
