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

    private String sku;

    private Long brandId;

    private Long firstCategoryId;

    private Long secondCategoryId;

    private Long thirdCategoryId;

    private Long templateId;

    private Integer isEnableSpec;




}
