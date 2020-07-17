package com.square.mall.member.center.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 登录信息
 *
 * @author Gencent
 * @date 2020/7/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "LoginDto", description = "登录信息")
public class LoginDto implements Serializable {

    private static final long serialVersionUID = 2073483643035364618L;

    /**
     * 会员ID
     */
    @ApiModelProperty(name = "memberId", value = "会员ID")
    private String memberId;

    /**
     * 手机号码
     */
    @ApiModelProperty(name = "mobile", value = "手机号码", required = true, example = "13500000001")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[345789][0-9]{9}$", message = "手机号格式有误")
    private String mobile;

    /**
     * token
     */
    @ApiModelProperty(name = "token", value = "令牌")
    private String token;

    /**
     * token失效时间，单位分钟，默认为1440
     */
    @ApiModelProperty(name = "expireTime", value = "token失效时间，单位分钟，默认为1440")
    private Integer expireTime;

    /**
     * 登录失败次数
     */
    @ApiModelProperty(name = "tryTimes", value = "登录失败次数")
    private Integer tryTimes;

}
