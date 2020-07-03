package com.square.mall.member.center.service.eo;

import com.square.mall.common.eo.BaseEo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 会员基本信息
 *
 * @author Gencent
 * @date 2019/8/20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MemberEo")
public class MemberEo extends BaseEo {

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
    @ApiModelProperty(name = "gender", value = "性别")
    private Integer gender;

    /**
     * 邮箱
     */
    @ApiModelProperty(name = "email", value = "邮箱")
    private String email;

}