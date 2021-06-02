package com.square.mall.item.center.biz.eo;

import com.square.mall.common.eo.BaseEo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 商品详情
 *
 * @author Gencent
 * @date 2020/7/29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ItemDescEo", description = "商品详情")
public class ItemDescEo extends BaseEo {

    private static final long serialVersionUID = -7724045451016246567L;

    /**
     * 介绍
     */
    @ApiModelProperty(name = "introduction", value = "介绍")
    private String introduction;

    /**
     * 图片
     */
    @ApiModelProperty(name = "image", value = "图片")
    private String image;

    /**
     * 包装列表
     */
    @ApiModelProperty(name = "packetList", value = "包装列表")
    private String packetList;

    /**
     * 售后服务
     */
    @ApiModelProperty(name = "saleService", value = "售后服务")
    private String saleService;

}
