package com.square.mall.item.center.service.eo;

import com.square.mall.common.eo.BaseEo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 扩展属性
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ExtraAttributesEo", description = "扩展属性")
public class ExtraAttributesEo extends BaseEo {

    private static final long serialVersionUID = 7779882405794618081L;

    /**
     * 名称
     */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    /**
     * 规格ID
     */
    @ApiModelProperty(name = "templateId", value = "模板ID")
    private Long templateId;

}
