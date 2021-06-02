package com.square.mall.item.center.biz.eo;

import com.square.mall.common.eo.BaseEo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 模板品牌
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TemplateBrandEo", description = "模板品牌")
public class TemplateBrandEo extends BaseEo {

    private static final long serialVersionUID = 8350996009721781137L;

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
