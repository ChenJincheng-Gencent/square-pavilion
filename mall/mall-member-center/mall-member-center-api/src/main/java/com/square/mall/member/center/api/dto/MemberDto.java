package com.square.mall.member.center.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "^1[34578][0-9]{9}$", message = "手机号必须满足格式")
    private String mobile;

    /**
     * 等级
     */
    @ApiModelProperty(name = "level", value = "等级", allowableValues = "1,2,3,4,5,6,7")
    @Range(min = 1L, max = 7L, message = "等级只能是0到7间的值")
    private Integer level;

    /**
     * 性别
     */
    @ApiModelProperty(name = "gender", value = "性别", allowableValues = "0,1,2")
    @Range(max = 2L, message = "性别只能是0到2间的值")
    private Integer gender;

    /**
     * 邮箱
     */
    @ApiModelProperty(name = "email", value = "邮箱")
    @Email(message = "邮箱必须满足格式")
    private String email;

}
