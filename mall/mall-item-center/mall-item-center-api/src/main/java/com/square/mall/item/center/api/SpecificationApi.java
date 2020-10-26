package com.square.mall.item.center.api;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.SpecificationGroupDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 规格API
 *
 * @author Gencent
 * @date 2020/7/27
 */
@FeignClient(name="mall-item-center")
public interface SpecificationApi {

    /**
     * 插入规格组合
     *
     * @param specificationGroupDto 规格组合
     * @return 数据库ID
     */
    @PostMapping("/specification/group")
    RspDto<Long> insertSpecificationGroup(@RequestBody SpecificationGroupDto specificationGroupDto);

    /**
     * 更新规格组合
     *
     * @param specificationGroupDto 规格组合
     * @return 响应
     */
    @PutMapping("/specification/group")
    RspDto updateSpecificationGroup(@RequestBody SpecificationGroupDto specificationGroupDto);

    /**
     * 批量删除规格组合
     *
     * @param ids 规格ID数组
     * @return 响应
     */
    @DeleteMapping("/specification/group/batch/ids")
    RspDto batchDeleteSpecificationGroup(@RequestParam("ids") Long[] ids);

    /**
     * 根据规格ID查询规格组合
     *
     * @param specId 规格ID
     * @return 规格组合
     */
    @GetMapping("/specification/group/spec-id")
    RspDto<SpecificationGroupDto> selectSpecificationGroupBySpecId(@RequestParam("specId") Long specId);

    /**
     * 分页条件查询规格列表
     *
     * @param specificationDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 规格列表
     */
    @PostMapping("/specification/list/page/condition")
    PageRspDto<List<SpecificationDto>> selectPageSpecificationByCondition(@RequestBody SpecificationDto specificationDto,
                                  @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);

    /**
     * 根据ID查询规格
     *
     * @param id ID
     * @return 规格
     */
    @GetMapping("/specification/id")
    RspDto<SpecificationDto> selectSpecificationById(@RequestParam("id") Long id);

    /**
     * 查询所有规格列表
     *
     * @return 规格列表
     */
    @GetMapping("/specification/list/all")
    RspDto<List<SpecificationDto>> selectSpecificationAll();

    /**
     * 条件查询规格列表
     *
     * @param specificationDto 查询条件
     * @return 规格列表
     */
    @PostMapping("/specification/list/condition")
    RspDto<List<SpecificationDto>> selectSpecificationByCondition(@RequestBody SpecificationDto specificationDto);

}
