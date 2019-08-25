package com.juncheng.mall.common.eo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础EO实体类
 *
 * @author Gencent
 * @date 2019/8/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEo implements Serializable {

    private static final long serialVersionUID = -8752680305912189331L;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 创建人
     */
    private String createPerson;

    /**
     * 更新人
     */
    private String updatePerson;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    private Short isDelete;

}
