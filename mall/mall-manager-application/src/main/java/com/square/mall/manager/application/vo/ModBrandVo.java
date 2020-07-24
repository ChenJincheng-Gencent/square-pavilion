package com.square.mall.manager.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 修改品牌VO
 *
 * @author Gencent
 * @date 2020/7/24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ModBrandVo", description = "修改品牌")
public class ModBrandVo implements Serializable {

    private static final long serialVersionUID = -2416555992650740664L;

    /**
     * 数据库ID
     */
    @NotNull(message = "数据库ID不能为空")
    @Min(value = 0L, message = "数据库ID最小值为1")
    @ApiModelProperty(name = "id", value = "数据库ID", required = true, example = "1")
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
