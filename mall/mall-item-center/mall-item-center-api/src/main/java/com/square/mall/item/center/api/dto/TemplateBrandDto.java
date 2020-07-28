package com.square.mall.item.center.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 模板品牌DTO
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TemplateBrandDto", description = "模板品牌")
public class TemplateBrandDto implements Serializable {

    private static final long serialVersionUID = -6599446439598613229L;

    /**
     * 数据库ID
     */
    @ApiModelProperty(name = "id", value = "数据库ID")
    private Long id;

    /**
     * 模板ID
     */
    @ApiModelProperty(name = "templateId", value = "模板ID")
    private Long templateId;

    /**
     * 品牌ID
     */
    @ApiModelProperty(name = "brandId", value = "品牌ID")
    private Long brandId;

}
