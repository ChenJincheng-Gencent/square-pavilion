package com.square.mall.job.application.eo;

import com.square.mall.common.eo.BaseEo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 品牌
 *
 * @author Gencent
 * @date 2020/7/23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "BrandEo", description = "品牌")
public class BrandEo extends BaseEo {

    private static final long serialVersionUID = 7815245678718352993L;

    /**
     * 名称
     */
    @ApiModelProperty(name = "name", value = "名称")
    private String name;

    /**
     * 首字母
     */
    @ApiModelProperty(name = "firstChar", value = "首字母")
    private String firstChar;

}
