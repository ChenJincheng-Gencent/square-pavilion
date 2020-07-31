package com.square.mall.manager.application.controller;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.SpecificationGroupDto;
import com.square.mall.manager.application.service.SpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 规格Controller
 *
 * @author Gencent
 * @date 2020/7/30
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/manager/v1")
@Slf4j
@Validated
@Api(tags = "规格REST API")
public class SpecificationController {

    @Resource
    private SpecificationService specificationService;

    /**
     * 分页条件查询规格列表
     *
     * @param specificationDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 规格列表
     */
    @PostMapping("/specification/list/page/condition")
    @ResponseBody
    @ApiOperation(value = "分页条件查询规格列表")
    public PageRspDto<List<SpecificationDto>> selectPageSpecificationByCondition(@RequestBody SpecificationDto specificationDto,
        @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize)  {

        PageRspDto<List<SpecificationDto>> specificationDtoList = specificationService.selectPageSpecificationByCondition(specificationDto, pageNum, pageSize);
        log.info("specificationDtoList: {}, specificationDto: {}, pageNum: {}, pageSize: {}", specificationDtoList, specificationDto, pageNum, pageSize);

        return specificationDtoList;

    }

    /**
     * 插入规格组合
     *
     * @param specificationGroupDto 规格组合
     * @return 数据库ID
     */
    @PostMapping("/specification/group")
    @ResponseBody
    @ApiOperation(value = "插入规格组合")
    public RspDto insertSpecificationGroup(@RequestBody @Valid SpecificationGroupDto specificationGroupDto) {
        RspDto<Long> id = specificationService.insertSpecificationGroup(specificationGroupDto);
        log.info("id: {}, specificationDto: {}", id.getData(), specificationGroupDto);
        return id;
    }

    /**
     * 更新规格组合
     *
     * @param specificationGroupDto 规格组合
     * @return 响应
     */
    @PutMapping("/specification/group")
    @ResponseBody
    @ApiOperation(value = "更新规格组合")
    public RspDto updateSpecification(@RequestBody @Valid SpecificationGroupDto specificationGroupDto) {

        return specificationService.updateSpecificationGroup(specificationGroupDto);
    }

    /**
     * 批量删除规格组合
     *
     * @param ids 规格ID数组
     * @return 响应
     */
    @DeleteMapping("/specification/group/batch")
    @ResponseBody
    @ApiOperation(value = "批量删除规格组合")
    public RspDto deleteSpecificationGroup(Long[] ids) {
        return specificationService.batchDeleteSpecificationGroup(ids);
    }

    /**
     * 根据规格ID查询规格组合
     *
     * @param specId 规格ID
     * @return 规格组合
     */
    @GetMapping("/specification/group")
    @ResponseBody
    @ApiOperation(value = "根据规格ID查询规格组合")
    public RspDto<SpecificationGroupDto> selectSpecificationGroupBySpecId(@RequestParam("specId")
        @NotNull(message = "规格ID不能为空") Long specId)  {

        RspDto<SpecificationGroupDto> specificationGroupDto = specificationService.selectSpecificationGroupBySpecId(specId);
        log.info("specificationGroupDto: {}, specId: {}", specificationGroupDto, specId);

        return specificationGroupDto;

    }

    /**
     * 查询所有规格列表
     *
     * @return 规格列表
     */
    @GetMapping("/specification/all")
    @ResponseBody
    @ApiOperation(value = "查询所有规格列表")
    public RspDto<List<SpecificationDto>> selectSpecificationAll()  {

        return specificationService.selectSpecificationAll();

    }

}
