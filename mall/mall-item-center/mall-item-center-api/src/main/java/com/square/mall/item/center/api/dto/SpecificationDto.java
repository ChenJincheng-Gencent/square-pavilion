package com.square.mall.item.center.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 规格DTO
 *
 * @author Gencent
 * @date 2020/7/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SpecificationDto", description = "规格")
public class SpecificationDto implements Serializable {

    private static final long serialVersionUID = -2902449864511149661L;

    private Long id;

    private String name;

}
