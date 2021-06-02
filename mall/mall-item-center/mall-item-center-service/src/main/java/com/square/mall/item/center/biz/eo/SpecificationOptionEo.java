package com.square.mall.item.center.biz.eo;

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
public class SpecificationOptionEo extends BaseEo {

    private static final long serialVersionUID = -831298922386373834L;

    /**
     * 名称
     */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    /**
     * 排序
     */
    @ApiModelProperty(name = "sort", value = "排序")
    private Integer sort;

    /**
     * 规格ID
     */
    @ApiModelProperty(name = "specId", value = "规格ID")
    private Long specId;

}
