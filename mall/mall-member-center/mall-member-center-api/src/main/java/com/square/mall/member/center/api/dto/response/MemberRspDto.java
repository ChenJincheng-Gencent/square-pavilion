package com.square.mall.member.center.api.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  会员返回信息
 * @author Gencent
 * @date 2019/8/21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MemberRspDto", description = "会员返回信息")
public class MemberRspDto implements Serializable {

    private static final long serialVersionUID = 8753491498155471509L;

    /**
     * 数据库ID
     */
    @ApiModelProperty(name = "id", value = "数据库ID")
    private Long id;

    /**
     * 昵称
     */
    @ApiModelProperty(name = "name", value = "昵称", example = "xxx")
    private String name;

    /**
     * 手机号码
     */
    @ApiModelProperty(name = "mobile", value = "手机号码", example = "13500000001")
    private String mobile;

    /**
     * 等级
     */
    @ApiModelProperty(name = "level", value = "等级", allowableValues = "1,2,3,4,5,6,7", example = "1")
    private Integer level;

    /**
     * 性别
     */
    @ApiModelProperty(name = "gender", value = "性别", allowableValues = "0,1,2", example = "0")
    private Integer gender;

    /**
     * 邮箱
     */
    @ApiModelProperty(name = "email", value = "邮箱", example = "13500000001@139.com")
    private String email;

}
