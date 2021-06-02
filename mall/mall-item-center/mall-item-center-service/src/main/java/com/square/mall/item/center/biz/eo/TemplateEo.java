package com.square.mall.item.center.biz.eo;

import com.square.mall.common.eo.BaseEo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 模板
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TemplateEo", description = "模板")
public class TemplateEo extends BaseEo {

    private static final long serialVersionUID = -31227881974091656L;

    /**
     * 名称
     */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

}
