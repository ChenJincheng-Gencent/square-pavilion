package com.square.mall.item.center.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 规格选项
 *
 * @author Gencent
 * @date 2020/7/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SpecificationOptionDto", description = "规格选项")
public class SpecificationOptionDto implements Serializable {

    private static final long serialVersionUID = 8051521745575588121L;

}
