package com.square.mall.item.center.biz.eo;

import com.square.mall.common.eo.BaseEo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 模板规格
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TemplateSpecificationEo", description = "模板规格")
public class TemplateSpecificationEo extends BaseEo {

    private static final long serialVersionUID = -861791662262467854L;

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
