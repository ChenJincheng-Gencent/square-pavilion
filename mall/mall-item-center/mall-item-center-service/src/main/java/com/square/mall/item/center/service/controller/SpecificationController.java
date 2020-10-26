package com.square.mall.item.center.service.controller;

import com.square.mall.common.dto.PageRspDto;
import com.square.mall.common.dto.RspDto;
import com.square.mall.common.util.DatabaseOptConstant;
import com.square.mall.common.util.DatabaseUtil;
import com.square.mall.common.util.ListUtil;
import com.square.mall.common.util.ModuleConstant;
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
    public RspDto<Long> insertSpecificationGroup(@RequestBody SpecificationGroupDto specificationGroupDto) {

        if (null == specificationGroupDto) {
            log.error("specificationGroupDto is null.");
            return DatabaseUtil.getResult(DatabaseOptConstant.DATABASE_PARA_ILLEGAL, ModuleConstant.ITEM_CENTER);
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
        return DatabaseUtil.getResult(DatabaseOptConstant.DATABASE_OPT_SUCCESS, specificationDto.getId(), ModuleConstant
                .ITEM_CENTER);
    }

    /**
     * 更新规格组合
     *
     * @param specificationGroupDto 规格组合
     * @return 响应
     */
    @PutMapping("/group")
    public RspDto updateSpecificationGroup(@RequestBody SpecificationGroupDto specificationGroupDto) {

        if (null == specificationGroupDto) {
            log.error("specificationGroupDto is null.");
            return DatabaseUtil.getResult(DatabaseOptConstant.DATABASE_PARA_ILLEGAL, ModuleConstant.ITEM_CENTER);
        }
        SpecificationDto specificationDto = specificationGroupDto.getSpecificationDto();
        int success = specificationService.updateSpecification(specificationDto);
        if (DatabaseOptConstant.DATABASE_OPT_SUCCESS != success) {
            return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);
        }
        Long specId = specificationDto.getId();
        specificationOptionService.deleteSpecificationOptionBySpecId(specId);
        List<SpecificationOptionDto> specificationOptionDtoList = specificationGroupDto.getSpecificationOptionDtoList();
        if (null != specificationOptionDtoList) {
            specificationOptionDtoList.forEach( x -> {
                x.setSpecId(specId);
                specificationOptionService.insertSpecificationOption(x);
            });
        }

        return DatabaseUtil.getResult(success, ModuleConstant.ITEM_CENTER);

    }

    /**
     * 批量删除规格组合
     *
     * @param ids 规格ID数组
     * @return 响应
     */
    @DeleteMapping("/group/batch/ids")
    public RspDto batchDeleteSpecificationGroup(@RequestParam("ids") Long[] ids) {

        if (null == ids) {
            log.error("ids is null.");
            return DatabaseUtil.getResult(DatabaseOptConstant.DATABASE_PARA_ILLEGAL, ModuleConstant.ITEM_CENTER);
        }
        for (Long id : ids) {
            specificationOptionService.deleteSpecificationOptionBySpecId(id);
            specificationService.deleteSpecification(id);
        }
        return DatabaseUtil.getResult(DatabaseOptConstant.DATABASE_OPT_SUCCESS, ModuleConstant.ITEM_CENTER);
    }

    /**
     * 根据规格ID查询规格组合
     *
     * @param specId 规格ID
     * @return 规格组合
     */
    @GetMapping("/group/spec-id")
    public RspDto<SpecificationGroupDto> selectSpecificationGroupBySpecId(@RequestParam("specId") Long specId) {
        SpecificationDto specificationDto = specificationService.selectSpecificationById(specId);
        if (null == specificationDto) {
            log.error("specificationDto is null. specId: {}", specId);
            return new RspDto<>(null);
        }
        SpecificationGroupDto specificationGroupDto = new SpecificationGroupDto();
        specificationGroupDto.setSpecificationDto(specificationDto);
        List<SpecificationOptionDto> optionDtoList = specificationOptionService.selectSpecificationOptionBySpecId(specId);
        specificationGroupDto.setSpecificationOptionDtoList(optionDtoList);
        return new RspDto<>(specificationGroupDto);
    }

    /**
     * 分页条件查询规格列表
     *
     * @param specificationDto 查询条件
     * @param pageNum 当前页
     * @param pageSize 分页大小
     * @return 规格列表
     */
    @GetMapping("/list/page/condition")
    public PageRspDto<List<SpecificationDto>> selectPageSpecificationByCondition(@RequestBody SpecificationDto specificationDto,
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
    public RspDto<SpecificationDto> selectSpecificationById(@RequestParam("id") Long id) {
        return new RspDto<>(specificationService.selectSpecificationById(id));
    }

    /**
     * 查询所有规格列表
     *
     * @return 规格列表
     */
    @GetMapping("/list/all")
    public RspDto<List<SpecificationDto>> selectSpecificationAll() {
        return new RspDto<>(specificationService.selectSpecificationAll());
    }

}
