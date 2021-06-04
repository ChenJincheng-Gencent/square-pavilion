package com.square.mall.manager.application.controller;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;
import com.square.mall.common.util.ListUtil;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.SpecificationGroupDto;
import com.square.mall.manager.application.service.SpecificationService;
import com.square.mall.manager.application.vo.Select2Vo;
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
import java.util.ArrayList;
import java.util.List;

/**
 * 规格Controller
 *
 * @author Gencent
 * @date 2020/7/30
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/specification")
@Slf4j
@Validated
@Api(tags = "规格")
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
    @PostMapping("/selectPageSpecificationByCondition")
    @ResponseBody
    @ApiOperation(value = "分页条件查询规格列表")
    public CommonPageRes<List<SpecificationDto>> selectPageSpecificationByCondition(@RequestBody SpecificationDto specificationDto,
                                                                                    @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize)  {

        CommonPageRes<List<SpecificationDto>> specificationDtoList = specificationService.selectPageSpecificationByCondition(specificationDto, pageNum, pageSize);
        log.info("specificationDtoList: {}, specificationDto: {}, pageNum: {}, pageSize: {}", specificationDtoList, specificationDto, pageNum, pageSize);

        return specificationDtoList;

    }

    /**
     * 插入规格组合
     *
     * @param specificationGroupDto 规格组合
     * @return 数据库ID
     */
    @PostMapping("/insertSpecificationGroup")
    @ResponseBody
    @ApiOperation(value = "插入规格组合")
    public CommonRes<Long> insertSpecificationGroup(@RequestBody @Valid SpecificationGroupDto specificationGroupDto) {
        CommonRes<Long> id = specificationService.insertSpecificationGroup(specificationGroupDto);
        log.info("id: {}, specificationGroupDto: {}", id.getData(), specificationGroupDto);
        return id;
    }

    /**
     * 更新规格组合
     *
     * @param specificationGroupDto 规格组合
     * @return 响应
     */
    @PutMapping("/updateSpecification")
    @ResponseBody
    @ApiOperation(value = "更新规格组合")
    public CommonRes<Void> updateSpecification(@RequestBody @Valid SpecificationGroupDto specificationGroupDto) {

        return specificationService.updateSpecificationGroup(specificationGroupDto);
    }

    /**
     * 批量删除规格组合
     *
     * @param ids 规格ID数组
     * @return 响应
     */
    @DeleteMapping("/deleteSpecificationGroup")
    @ResponseBody
    @ApiOperation(value = "批量删除规格组合")
    public CommonRes<Void> deleteSpecificationGroup(Long[] ids) {
        return specificationService.batchDeleteSpecificationGroup(ids);
    }

    /**
     * 根据规格ID查询规格组合
     *
     * @param specId 规格ID
     * @return 规格组合
     */
    @GetMapping("/selectSpecificationGroupBySpecId")
    @ResponseBody
    @ApiOperation(value = "根据规格ID查询规格组合")
    public CommonRes<SpecificationGroupDto> selectSpecificationGroupBySpecId(@RequestParam("specId")
        @NotNull(message = "规格ID不能为空") Long specId)  {

        CommonRes<SpecificationGroupDto> specificationGroupDto = specificationService.selectSpecificationGroupBySpecId(specId);
        log.info("specificationGroupDto: {}, specId: {}", specificationGroupDto, specId);

        return specificationGroupDto;

    }

    /**
     * 查询所有规格列表
     *
     * @return 规格列表
     */
    @GetMapping("/selectSpecificationAll")
    @ResponseBody
    @ApiOperation(value = "查询所有规格列表")
    public CommonRes<List<Select2Vo>> selectSpecificationAll()  {

        List<SpecificationDto> specificationDtoList = specificationService.selectSpecificationAll().getData();
        if (ListUtil.isBlank(specificationDtoList)) {
            return new CommonRes<>(null);
        }
        List<Select2Vo> select2VoList = new ArrayList<>();
        specificationDtoList.forEach( x -> {
            Select2Vo select2Vo = new Select2Vo();
            select2Vo.setId(x.getId());
            select2Vo.setText(x.getName());
            select2VoList.add(select2Vo);
        });

        return new CommonRes<>(select2VoList);

    }

}
