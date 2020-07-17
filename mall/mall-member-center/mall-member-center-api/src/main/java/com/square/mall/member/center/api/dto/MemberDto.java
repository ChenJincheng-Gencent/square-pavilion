package com.square.mall.member.center.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 会员信息DTO
 *
 * @author Gencent
 * @date 2020/7/13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MemberDto", description = "会员信息")
public class MemberDto implements Serializable {

    private static final long serialVersionUID = -1315053788575053945L;

    /**
     * 昵称
     */
    @ApiModelProperty(name = "name", value = "昵称", example = "xxx")
    @Length(max = 20, message = "昵称不能超过20个字符")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "昵称限制：最多20字符，包含文字、字母和数字")
    private String name;

    /**
     * 手机号码
     */
    @ApiModelProperty(name = "mobile", value = "手机号码", required = true, example = "13500000001")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^1[345789][0-9]{9}$", message = "手机号格式有误")
    private String mobile;

    /**
     * 等级
     */
    @ApiModelProperty(name = "level", value = "等级", allowableValues = "1,2,3,4,5,6,7", example = "1")
    @Range(min = 1L, max = 7L, message = "等级只能是0到7之间的值")
    private Integer level;

    /**
     * 性别
     */
    @ApiModelProperty(name = "gender", value = "性别", allowableValues = "0,1,2", example = "0")
    @Range(max = 2L, message = "性别只能是0到2之间的值")
    private Integer gender;

    /**
     * 邮箱
     */
    @ApiModelProperty(name = "email", value = "邮箱", example = "13500000001@139.com")
    @Email(message = "邮箱格式有误")
    private String email;

}
