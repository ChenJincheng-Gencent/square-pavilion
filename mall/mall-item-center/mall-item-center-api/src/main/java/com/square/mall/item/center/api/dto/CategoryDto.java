package com.square.mall.item.center.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 分类DTO
 *
 * @author Gencent
 * @date 2020/8/10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CategoryDto", description = "分类")
public class CategoryDto implements Serializable {

    private static final long serialVersionUID = 5101478995864878812L;

    /**
     * 数据库ID
     */
    @ApiModelProperty(name = "id", value = "数据库ID")
    private Long id;

    /**
     * 名称
     */
    @NotBlank
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,7}$|^[\\dA-Za-z_]{1,14}$",
            message = "最长不得超过7个汉字，或14个字节(数字，字母和下划线)")
    @ApiModelProperty(name = "name", value = "名称", required = true, example = "手机")
    private String name;

    /**
     * 上级ID
     */
    @ApiModelProperty(name = "parentId", value = "上级ID", example = "1")
    private Long parentId;

    /**
     * 模板ID
     */
    @ApiModelProperty(name = "templateId", value = "模板ID", example = "1")
    private Long templateId;

}
