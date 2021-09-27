package com.yimi.oysc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yimi.oysc.entity.RoleEntity;
import com.yimi.oysc.service.IRoleService;
import com.yimi.oysc.utils.CommonUtils;
import com.yimi.oysc.vo.add.AddRoleVO;
import com.yimi.oysc.vo.common.PageResult;
import com.yimi.oysc.vo.common.PageWrapper;
import com.yimi.oysc.vo.common.ResultVO;
import com.yimi.oysc.vo.select.SelectRoleVO;
import com.yimi.oysc.vo.update.UpdateRoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Davy
 * @since 2021-09-24
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色管理模块")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping("/add")
    @ApiOperation("新增角色")
    public ResultVO<RoleEntity> add(@Valid @RequestBody AddRoleVO vo) {

        RoleEntity role = roleService.add(vo, CommonUtils.getLoginUsername());

        return ResultVO.successResult(role);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除角色")
    public ResultVO<Boolean> delete(@PathVariable("id") Integer id) {
        boolean b = roleService.removeById(id);
        if (b) {
            return ResultVO.successResult(b);
        } else {
            return ResultVO.errorResult(1, "删除角色失败");
        }
    }

    @PutMapping("/update")
    @ApiOperation("修改角色")
    public ResultVO<RoleEntity> update(@Valid @RequestBody UpdateRoleVO vo) {
        RoleEntity role = roleService.update(vo, CommonUtils.getLoginUsername());

        return ResultVO.successResult(role);
    }

    @GetMapping("/list")
    @ApiOperation("分页查询角色")
    public ResultVO<PageResult<RoleEntity>> list(@Valid PageWrapper<RoleEntity> pageWrapper, SelectRoleVO selectRoleVO) {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(CommonUtils.classCopy(selectRoleVO, RoleEntity.class));
        Page<RoleEntity> page = roleService.page(pageWrapper.toDbPage(), queryWrapper);
        return PageResult.toVoPage(page);
    }

}
