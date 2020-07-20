package com.square.mall.member.center.service.eo;

import com.square.mall.common.eo.BaseEo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 收货地址
 *
 * @author Gencent
 * @date 2020/7/20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AddressEo", description = "收货地址")
public class AddressEo extends BaseEo {

    private static final long serialVersionUID = -4577826199152638502L;

    /**
     * 会员ID
     */
    @ApiModelProperty(name = "memberId", value = "会员ID")
    private Long memberId;

    /**
     * 收货人
     */
    @ApiModelProperty(name = "receiver", value = "收货人")
    private String receiver;

    /**
     * 手机号码
     */
    @ApiModelProperty(name = "mobile", value = "手机号码")
    private String mobile;

    /**
     * 省份
     */
    @ApiModelProperty(name = "province", value = "省份")
    private String province;

    /**
     * 城市
     */
    @ApiModelProperty(name = "city", value = "城市")
    private String city;

    /**
     * 区县
     */
    @ApiModelProperty(name = "county", value = "区县")
    private String county;

    /**
     * 街道/乡镇
     */
    @ApiModelProperty(name = "street", value = "街道/乡镇")
    private String street;

    /**
     * 详细地址
     */
    @ApiModelProperty(name = "detail", value = "详细地址")
    private String detail;

    /**
     * 是否默认地址
     */
    @ApiModelProperty(name = "isDefault", value = "是否默认地址")
    private Integer isDefault;

}
