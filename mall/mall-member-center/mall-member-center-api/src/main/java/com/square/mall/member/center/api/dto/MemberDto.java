package com.square.mall.member.center.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 会员基本信息DTO
 *
 * @author Gencent
 * @date 2020/7/13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MemberDto")
public class MemberDto implements Serializable {

    private static final long serialVersionUID = -1315053788575053945L;

    /**
     * 姓名
     */
    @ApiModelProperty(name = "name", value = "姓名")
    private String name;

    /**
     * 手机号码
     */
    @ApiModelProperty(name = "mobile", value = "手机号码")
    private String mobile;

    /**
     * 等级
     */
    @ApiModelProperty(name = "level", value = "等级")
    private Integer level;

    /**
     * 性别
     */
    @ApiModelProperty(name = "gender", value = "性别", allowableValues = "0未知,1男,2女")
    private Integer gender;

    /**
     * 邮箱
     */
    @ApiModelProperty(name = "email", value = "邮箱")
    private String email;

}
