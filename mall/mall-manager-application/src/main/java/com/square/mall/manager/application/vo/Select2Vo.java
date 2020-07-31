package com.square.mall.manager.application.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Select2数据格式
 *
 * @author Gencent
 * @date 2020/7/31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Select2Vo", description = "Select2数据格式")
public class Select2Vo implements Serializable {

    private static final long serialVersionUID = -3518868368329403214L;

    @ApiModelProperty(name = "id", value = "数据库ID", example = "1")
    private Long id;

    @ApiModelProperty(name = "text", value = "名称", example = "小米")
    private String text;

}
