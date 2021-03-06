package com.square.mall.item.center.api;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;
import com.square.mall.item.center.api.dto.TemplateDto;
import com.square.mall.item.center.api.dto.TemplateGroupDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 模板API
 *
 * @author Gencent
 * @date 2020/7/31
 */
@FeignClient(contextId = "item-template", name="mall-item-center")
public interface TemplateApi {

    /**
     * 插入模板组合
     *
     * @param templateGroupDto 模板组合
     * @return 数据库ID
     */
    @PostMapping("/template/group")
    CommonRes<Long> insertTemplateGroup(@RequestBody TemplateGroupDto templateGroupDto);

    /**
     * 更新模板组合
     *
     * @param templateGroupDto 模板组合
     * @return 响应
     */
    @PutMapping("/template/group")
    CommonRes<Void> updateTemplateGroup(@RequestBody TemplateGroupDto templateGroupDto);

    /**
     * 批量删除模板组合
     *
     * @param ids 模板ID数组
     * @return 响应
     */
    @DeleteMapping("/template/group/batch/ids")
    CommonRes<Void> batchDeleteTemplateGroup(@RequestParam("ids") Long[] ids);

    /**
     * 根据模板ID查询模板组合
     *
     * @param templateId 模板ID
     * @return 模板组合
     */
    @GetMapping("/template/group/template-id")
    CommonRes<TemplateGroupDto> selectTemplateGroupByTemplateId(@RequestParam("templateId") Long templateId);

    /**
     * 分页条件查询模板组合列表
     *
     * @param templateDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 模板组合列表
     */
    @PostMapping("/template/group/list/page/condition")
    CommonPageRes<List<TemplateGroupDto>> selectPageTemplateGroupByCondition(@RequestBody TemplateDto templateDto,
                                                                             @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);


}
