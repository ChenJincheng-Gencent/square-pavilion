package com.square.mall.item.center.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 模板规格DTO
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TemplateSpecificationDto", description = "模板规格")
public class TemplateSpecificationDto implements Serializable {

    private static final long serialVersionUID = -536367769436883783L;

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
     * 规格ID
     */
    @ApiModelProperty(name = "specId", value = "规格ID")
    private Long specId;

}
