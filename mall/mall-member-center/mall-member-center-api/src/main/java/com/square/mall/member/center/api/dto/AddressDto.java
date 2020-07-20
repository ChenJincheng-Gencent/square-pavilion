package com.square.mall.member.center.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 收货地址DTO
 *
 * @author Gencent
 * @date 2020/7/20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "AddressDto", description = "收货地址")
public class AddressDto implements Serializable {

    private static final long serialVersionUID = 7626193990345770251L;

    /**
     * 数据库ID
     */
    @ApiModelProperty(name = "id", value = "数据库ID")
    private Long id;

    /**
     * 会员ID
     */
    @NotNull(message = "会员ID不能为空")
    @ApiModelProperty(name = "memberId", value = "会员ID", required = true, example = "1")
    private Long memberId;

    /**
     * 收货人
     */
    @NotBlank(message = "收货人不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,7}$|^[\\dA-Za-z_]{1,14}$", message = "最长不得超过7个汉字，或14个字节(数字，字母和下划线)")
    @ApiModelProperty(name = "receiver", value = "收货人", required = true, example = "xxx")
    private String receiver;

    /**
     * 手机号码
     */
    @ApiModelProperty(name = "mobile", value = "手机号码", required = true, example = "13500000001")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[345789][0-9]{9}$", message = "手机号格式有误")
    private String mobile;

    /**
     * 省份
     */
    @NotBlank(message = "省份不能为空")
    @Length(max = 20, message = "省份不能超过20个字符")
    @ApiModelProperty(name = "province", value = "省份", required = true, example = "浙江省")
    private String province;

    /**
     * 城市
     */
    @Length(max = 20, message = "城市不能超过20个字符")
    @NotBlank(message = "城市不能为空")
    @ApiModelProperty(name = "city", value = "城市", required = true, example = "杭州市")
    private String city;

    /**
     * 区县
     */
    @Length(max = 20, message = "区县不能超过20个字符")
    @NotBlank(message = "区县不能为空")
    @ApiModelProperty(name = "county", value = "区县", required = true, example = "滨江区")
    private String county;

    /**
     * 街道/乡镇
     */
    @Length(max = 20, message = "街道/乡镇不能超过20个字符")
    @NotBlank(message = "街道/乡镇不能为空")
    @ApiModelProperty(name = "street", value = "街道/乡镇", required = true, example = "浦沿街道")
    private String street;

    /**
     * 详细地址
     */
    @Length(max = 50, message = "详细地址不能超过50个字符")
    @NotBlank(message = "详细地址不能为空")
    @ApiModelProperty(name = "detail", value = "详细地址", required = true, example = "xx小区xx幢xx单元xx号")
    private String detail;

    /**
     * 是否默认地址
     */
    @Range(max = 1L, message = "是否默认地址只能是0到1之间的值")
    @ApiModelProperty(name = "isDefault", value = "是否默认地址",allowableValues = "0,1", example = "0")
    private Integer isDefault;

}
