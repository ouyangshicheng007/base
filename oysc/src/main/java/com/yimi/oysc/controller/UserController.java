package com.yimi.oysc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yimi.oysc.entity.RoleEntity;
import com.yimi.oysc.entity.UserEntity;
import com.yimi.oysc.service.IUserService;
import com.yimi.oysc.utils.CommonUtils;
import com.yimi.oysc.vo.add.AddRoleVO;
import com.yimi.oysc.vo.add.AddUserVO;
import com.yimi.oysc.vo.common.PageResult;
import com.yimi.oysc.vo.common.PageWrapper;
import com.yimi.oysc.vo.common.ResultVO;
import com.yimi.oysc.vo.select.SelectRoleVO;
import com.yimi.oysc.vo.select.SelectUserVO;
import com.yimi.oysc.vo.update.UpdateRoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import springfox.documentation.service.Tags;

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
@RequestMapping("/user")
@Api(tags = "用户管理")
public class UserController {


    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/add")
    @ApiOperation("新增用户")
    public ResultVO<UserEntity> add(@Valid @RequestBody AddUserVO vo) {
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        UserEntity user = CommonUtils.classCopy(vo, UserEntity.class);

        user.setCreateTime(LocalDateTime.now());
        user.setCreateBy(CommonUtils.getLoginUsername());
        userService.save(user);

        user.setPassword("PROTECTED");
        return ResultVO.successResult(user);
    }


//    @PutMapping("/update")
//    @ApiOperation("修改用户")
//    public ResultVO<RoleEntity> update(@Valid @RequestBody UpdateRoleVO vo) {
//        RoleEntity role = new RoleEntity();
//        role.setId(vo.getId());
//        if (!ObjectUtils.isEmpty(vo.getCode())) {
//            role.setCode(vo.getCode());
//        }
//        if (!ObjectUtils.isEmpty(vo.getName())) {
//            role.setName(vo.getName());
//        }
//        if (vo.getStatus() != null) {
//            role.setStatus(vo.getStatus());
//        }
//        if (!ObjectUtils.isEmpty(vo.getRemark())) {
//            role.setRemark(vo.getRemark());
//        }
//
//        role.setUpdateTime(LocalDateTime.now());
//        role.setUpdateBy("admin");
//        roleService.updateById(role);
//
//        return ResultVO.successResult(role);
//    }

    @GetMapping("/list")
    @ApiOperation("分页查询用户")
    public ResultVO<PageResult<UserEntity>> list(@Valid PageWrapper<UserEntity> pageWrapper, SelectUserVO selectUserVO) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(CommonUtils.classCopy(selectUserVO, UserEntity.class));
        Page<UserEntity> page = userService.page(pageWrapper.toDbPage(), queryWrapper);
        page.getRecords().forEach(data -> data.setPassword("PROTECTED"));
        return PageResult.toVoPage(page);
    }
}
