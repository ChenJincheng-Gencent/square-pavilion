package com.square.mall.item.center.service.controller;

import com.square.mall.common.dto.CommonPageRes;
import com.square.mall.common.dto.CommonRes;
import com.square.mall.common.enums.ErrorCode;
import com.square.mall.common.util.ListUtil;
import com.square.mall.item.center.api.dto.SpecificationDto;
import com.square.mall.item.center.api.dto.SpecificationGroupDto;
import com.square.mall.item.center.api.dto.SpecificationOptionDto;
import com.square.mall.item.center.service.service.SpecificationOptionService;
import com.square.mall.item.center.service.service.SpecificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规格Controller
 *
 * @author Gencent
 * @date 2020/10/26
 */
@Slf4j
@RestController
@RequestMapping("/specification")
public class SpecificationController {

    @Resource
    private SpecificationService specificationService;

    @Resource
    private SpecificationOptionService specificationOptionService;

    /**
     * 插入规格组合
     *
     * @param specificationGroupDto 规格组合
     * @return 数据库ID
     */
    @PostMapping("/group")
    public CommonRes<Long> insertSpecificationGroup(@RequestBody SpecificationGroupDto specificationGroupDto) {

        if (null == specificationGroupDto) {
            log.error("specificationGroupDto is null.");
            return new CommonRes<>(ErrorCode.PARA_IS_NULL);
        }

        SpecificationDto specificationDto = specificationGroupDto.getSpecificationDto();
        specificationService.insertSpecification(specificationDto);
        List<SpecificationOptionDto> specificationOptionDtoList = specificationGroupDto.getSpecificationOptionDtoList();
        if (ListUtil.isNotBlank(specificationOptionDtoList)) {
            specificationOptionDtoList.forEach( x -> {
                x.setSpecId(specificationDto.getId());
                specificationOptionService.insertSpecificationOption(x);
            });
        }
        return new CommonRes<>(specificationDto.getId());
    }

    /**
     * 更新规格组合
     *
     * @param specificationGroupDto 规格组合
     * @return 响应
     */
    @PutMapping("/group")
    public CommonRes<Void> updateSpecificationGroup(@RequestBody SpecificationGroupDto specificationGroupDto) {

        if (null == specificationGroupDto) {
            log.error("specificationGroupDto is null.");
            return new CommonRes<>(ErrorCode.PARA_IS_NULL);
        }
        SpecificationDto specificationDto = specificationGroupDto.getSpecificationDto();
        int success = specificationService.updateSpecification(specificationDto);
        Long specId = specificationDto.getId();
        specificationOptionService.deleteSpecificationOptionBySpecId(specId);
        List<SpecificationOptionDto> specificationOptionDtoList = specificationGroupDto.getSpecificationOptionDtoList();
        if (null != specificationOptionDtoList) {
            specificationOptionDtoList.forEach( x -> {
                x.setSpecId(specId);
                specificationOptionService.insertSpecificationOption(x);
            });
        }

        return CommonRes.SUCCESS;

    }

    /**
     * 批量删除规格组合
     *
     * @param ids 规格ID数组
     * @return 响应
     */
    @DeleteMapping("/group/batch/ids")
    public CommonRes<Void> batchDeleteSpecificationGroup(@RequestParam("ids") Long[] ids) {

        if (null == ids) {
            log.error("ids is null.");
            return new CommonRes<>(ErrorCode.PARA_IS_NULL);
        }
        for (Long id : ids) {
            specificationOptionService.deleteSpecificationOptionBySpecId(id);
            specificationService.deleteSpecification(id);
        }
        return CommonRes.SUCCESS;
    }

    /**
     * 根据规格ID查询规格组合
     *
     * @param specId 规格ID
     * @return 规格组合
     */
    @GetMapping("/group/spec-id")
    public CommonRes<SpecificationGroupDto> selectSpecificationGroupBySpecId(@RequestParam("specId") Long specId) {
        SpecificationDto specificationDto = specificationService.selectSpecificationById(specId);
        if (null == specificationDto) {
            log.error("specificationDto is null. specId: {}", specId);
            return new CommonRes<>(null);
        }
        SpecificationGroupDto specificationGroupDto = new SpecificationGroupDto();
        specificationGroupDto.setSpecificationDto(specificationDto);
        List<SpecificationOptionDto> optionDtoList = specificationOptionService.selectSpecificationOptionBySpecId(specId);
        specificationGroupDto.setSpecificationOptionDtoList(optionDtoList);
        return new CommonRes<>(specificationGroupDto);
    }

    /**
     * 分页条件查询规格列表
     *
     * @param specificationDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 规格列表
     */
    @PostMapping("/list/page/condition")
    public CommonPageRes<List<SpecificationDto>> selectPageSpecificationByCondition(@RequestBody SpecificationDto specificationDto,
                                @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return specificationService.selectPageSpecificationByCondition(specificationDto, pageNum, pageSize);
    }

    /**
     * 根据ID查询规格
     *
     * @param id ID
     * @return 规格
     */
    @GetMapping("/id")
    public CommonRes<SpecificationDto> selectSpecificationById(@RequestParam("id") Long id) {
        return new CommonRes<>(specificationService.selectSpecificationById(id));
    }

    /**
     * 查询所有规格列表
     *
     * @return 规格列表
     */
    @GetMapping("/list/all")
    public CommonRes<List<SpecificationDto>> selectSpecificationAll() {
        return new CommonRes<>(specificationService.selectSpecificationAll());
    }

    /**
     * 条件查询规格列表
     *
     * @param specificationDto 查询条件
     * @return 规格列表
     */
    @PostMapping("/list/condition")
    public CommonRes<List<SpecificationDto>> selectSpecificationByCondition(@RequestBody SpecificationDto specificationDto) {
        return new CommonRes<>(specificationService.selectSpecificationByCondition(specificationDto));
    }

}
