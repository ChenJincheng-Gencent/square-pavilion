package com.square.mall.member.center.service.eo;

import com.square.mall.common.eo.BaseEo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 登录信息
 *
 * @author Gencent
 * @date 2020/7/17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "LoginEo", description = "登录信息")
public class LoginEo extends BaseEo {

    /**
     * 会员ID
     */
    @ApiModelProperty(name = "memberId", value = "会员ID")
    private String memberId;

    /**
     * 手机号码
     */
    @ApiModelProperty(name = "mobile", value = "手机号码")
    private String mobile;

    /**
     * 令牌
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
