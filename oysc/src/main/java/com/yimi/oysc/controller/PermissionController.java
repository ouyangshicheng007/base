package com.yimi.oysc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yimi.oysc.entity.PermissionEntity;
import com.yimi.oysc.entity.RoleEntity;
import com.yimi.oysc.service.IPermissionService;
import com.yimi.oysc.utils.CommonUtils;
import com.yimi.oysc.vo.add.AddPermissionVO;
import com.yimi.oysc.vo.common.PageResult;
import com.yimi.oysc.vo.common.PageWrapper;
import com.yimi.oysc.vo.common.ResultVO;
import com.yimi.oysc.vo.select.SelectRoleVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Davy
 * @since 2021-09-24
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @PostMapping("/add")
    @ApiOperation("新增权限资源")
    public ResultVO<PermissionEntity> add(@Valid @RequestBody AddPermissionVO vo) {

        PermissionEntity role = permissionService.add(vo, CommonUtils.getLoginUsername());

        return ResultVO.successResult(role);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除权限资源")
    public ResultVO<Boolean> delete(@PathVariable("id") Integer id) {

        return ResultVO.successResult(permissionService.deletePermission(id));
    }

    @GetMapping("/list")
    @ApiOperation("分页查询权限资源")
    public ResultVO<PageResult<PermissionEntity>> list(@Valid PageWrapper<RoleEntity> pageWrapper, SelectRoleVO selectRoleVO) {
        QueryWrapper<PermissionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(CommonUtils.classCopy(selectRoleVO, PermissionEntity.class));
        Page<PermissionEntity> page = permissionService.page(pageWrapper.toDbPage(), queryWrapper);
        return PageResult.toVoPage(page);
    }


    @GetMapping("/list/{roleId}")
    @ApiOperation("查询角色所有拥有的全部权限")
    public ResultVO<List<PermissionEntity>> listByRoleId(@PathVariable("roleId")Integer roleId) {

        List<PermissionEntity> list = permissionService.listByRoleId(roleId);

        return ResultVO.successResult(list);
    }

}
