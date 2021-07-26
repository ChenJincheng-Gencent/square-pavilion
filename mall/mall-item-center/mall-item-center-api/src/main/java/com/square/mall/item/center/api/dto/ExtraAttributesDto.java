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
 * 扩展属性DTO
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ExtraAttributesDto", description = "扩展属性")
public class ExtraAttributesDto implements Serializable {

    private static final long serialVersionUID = 8611223247183533815L;

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
    @ApiModelProperty(name = "name", value = "名称", required = true, example = "重量")
    private String name;

    /**
     * 模板ID
     */
    @ApiModelProperty(name = "templateId", value = "模板ID", example = "1")
    private Long templateId;

}
