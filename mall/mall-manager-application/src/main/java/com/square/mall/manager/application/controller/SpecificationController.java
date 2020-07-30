package com.square.mall.manager.application.controller;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.item.center.api.dto.SpecificationDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
     * 插入规格
     *
     * @param specificationDto 规格
     * @return 数据库ID
     */
    @PostMapping("/specification")
    @ResponseBody
    @ApiOperation(value = "插入规格")
    public RspDto insertSpecification(@RequestBody @Valid SpecificationDto specificationDto) {
        RspDto<Long> id = specificationService.insertSpecification(specificationDto);
        log.info("id: {}, specificationDto: {}", id.getData(), specificationDto);
        return id;
    }

    /**
     * 更新规格
     *
     * @param modSpecificationVo 规格
     * @return 响应
     */
    @PutMapping("/specification")
    @ResponseBody
    @ApiOperation(value = "更新规格")
    public RspDto updateSpecification(@RequestBody @Valid ModSpecificationVo modSpecificationVo) {
        SpecificationDto specificationDto = new SpecificationDto();
        BeanUtils.copyProperties(modSpecificationVo, specificationDto);
        return specificationService.updateSpecification(specificationDto);
    }

    /**
     * 批量删除规格
     *
     * @param ids ID数组
     * @return 响应
     */
    @DeleteMapping("/specification/batch")
    @ResponseBody
    @ApiOperation(value = "批量删除规格")
    public RspDto deleteSpecification(Long[] ids) {
        return specificationService.batchDeleteSpecification(ids);
    }

    /**
     * 根据ID查询规格
     *
     * @param id ID
     * @return 规格
     */
    @GetMapping("/specification")
    @ResponseBody
    @ApiOperation(value = "根据ID查询规格")
    public RspDto<SpecificationDto> selectSpecificationById(@RequestParam("id") @NotNull(message = "ID不能为空") Long id)  {

        RspDto<SpecificationDto> specificationDto = specificationService.selectSpecificationById(id);
        log.info("specificationDto: {}, id: {}", specificationDto, id);

        return specificationDto;

    }

}
