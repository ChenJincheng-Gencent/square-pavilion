package com.square.mall.item.center.service.eo;

import com.square.mall.common.eo.BaseEo;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 商品
 *
 * @author Gencent
 * @date 2020/7/28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ItemEoEo", description = "商品")
public class ItemEo extends BaseEo {

    private static final long serialVersionUID = 3446266424328123147L;


    /**
     * 名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 描述
     */
    private String desc;

    /**
     * 审核状态
     */
    private Integer auditStatus;

    /**
     * 上架状态
     */
    private Integer shelfStatus;

    /**
     * 是否热门
     */
    private Integer isHot;

    /**
     * 商品SKU
     */
    private String sku;

    /**
     * 品牌ID
     */
    private Long brandId;

    /**
     * 一级分类ID
     */
    private Long firstCategoryId;

    /**
     * 二级分类ID
     */
    private Long secondCategoryId;

    /**
     * 三级分类ID
     */
    private Long thirdCategoryId;

    /**
     * 模板ID
     */
    private Long templateId;

    /**
     * 是否开启规格
     */
    private Integer isEnableSpec;




}
