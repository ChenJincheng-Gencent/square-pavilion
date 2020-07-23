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
 * 品牌DTO
 *
 * @author Gencent
 * @date 2020/7/23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "BrandDto", description = "品牌")
public class BrandDto implements Serializable {

    private static final long serialVersionUID = 7597239081856357566L;

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
    @ApiModelProperty(name = "name", value = "名称", required = true, example = "小米")
    private String name;

    /**
     * 首字母
     */
    @NotBlank
    @ApiModelProperty(name = "firstChar", value = "首字母", required = true, example = "X")
    private String firstChar;

}
