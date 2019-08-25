package com.square.mall.member.center.service.eo;

import com.juncheng.mall.common.eo.BaseEo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会员基本信息
 *
 * @author Gencent
 * @date 2019/8/20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MemberEo extends BaseEo {

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 邮箱
     */
    private String email;

}
