package com.square.mall.item.center.service.eo;

import com.square.mall.common.eo.BaseEo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 分类
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CategoryEo", description = "分类")
public class CategoryEo extends BaseEo {

    private static final long serialVersionUID = -4151175680328560030L;

    /**
     * 名称
     */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    /**
     * 上级ID
     */
    @ApiModelProperty(name = "parentId", value = "上级ID")
    private Long parentId;

    /**
     * 模板ID
     */
    @ApiModelProperty(name = "templateId", value = "模板ID")
    private Long templateId;

}
