package com.square.mall.item.center.service.eo;

import com.square.mall.common.eo.BaseEo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 规格
 *
 * @author Gencent
 * @date 2020/7/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SpecificationEo", description = "规格")
public class SpecificationEo extends BaseEo {

    private static final long serialVersionUID = -8588182347483120661L;

    /**
     * 名称
     */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

}
