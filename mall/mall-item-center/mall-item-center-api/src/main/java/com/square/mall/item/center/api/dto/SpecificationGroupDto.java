package com.square.mall.item.center.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 规格组合DTO
 *
 * @author Gencent
 * @date 2020/7/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SpecificationGroupDto", description = "规格组合")
public class SpecificationGroupDto implements Serializable {

    private static final long serialVersionUID = -5332127399032375338L;

    /**
     * 规格
     */
    @ApiModelProperty(name = "specificationDto", value = "规格")
    private SpecificationDto specificationDto;

    /**
     * 规格选项列表
     */
    @ApiModelProperty(name = "specificationOptionDtoList", value = "规格选项列表")
    private List<SpecificationOptionDto> specificationOptionDtoList;

}
