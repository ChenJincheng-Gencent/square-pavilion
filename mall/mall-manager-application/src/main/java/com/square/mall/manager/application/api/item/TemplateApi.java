package com.square.mall.manager.application.api.item;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
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
@FeignClient(name="mall-item-center")
public interface TemplateApi {

    /**
     * 插入模板组合
     *
     * @param templateGroupDto 模板组合
     * @return 数据库ID
     */
    @PostMapping("/template/group")
    RspDto<Long> insertTemplateGroup(@RequestBody TemplateGroupDto templateGroupDto);

    /**
     * 更新模板组合
     *
     * @param templateGroupDto 模板组合
     * @return 响应
     */
    @PutMapping("/template/group")
    RspDto updateTemplateGroup(@RequestBody TemplateGroupDto templateGroupDto);

    /**
     * 批量删除模板组合
     *
     * @param ids 模板ID数组
     * @return 响应
     */
    @DeleteMapping("/template/group/batch/ids")
    RspDto batchDeleteTemplateGroup(@RequestParam("ids") Long[] ids);

    /**
     * 根据模板ID查询模板组合
     *
     * @param templateId 模板ID
     * @return 模板组合
     */
    @GetMapping("/template/group/template-id")
    RspDto<TemplateGroupDto> selectTemplateGroupByTemplateId(@RequestParam("templateId") Long templateId);

    /**
     * 分页条件查询模板组合列表
     *
     * @param templateDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 模板组合列表
     */
    @GetMapping("/template/group/list/page/condition")
    PageRspDto<List<TemplateGroupDto>> selectPageTemplateGroupByCondition(@RequestBody TemplateDto templateDto,
                                                                          @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);


}
