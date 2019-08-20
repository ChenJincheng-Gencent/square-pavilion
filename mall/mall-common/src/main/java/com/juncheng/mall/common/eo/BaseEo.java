package com.juncheng.mall.common.eo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
     * 创建人
     */
    private String createPerson;

    /**
     * 是否删除
     */
    private Short isDelete;

}
