package com.yimi.oysc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yimi.oysc.entity.RoleEntity;
import com.yimi.oysc.entity.RolePermissionRelEntity;
import com.yimi.oysc.exception.RoleNameExistException;
import com.yimi.oysc.mapper.RoleMapper;
import com.yimi.oysc.service.IRolePermissionRelService;
import com.yimi.oysc.service.IRoleService;
import com.yimi.oysc.utils.CommonUtils;
import com.yimi.oysc.vo.add.AddRoleVO;
import com.yimi.oysc.vo.update.AssignRoleVO;
import com.yimi.oysc.vo.update.UpdateRoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Davy
 * @since 2021-09-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements IRoleService {

    @Autowired
    private IRolePermissionRelService rolePermissionRelService;

    @Override
    public RoleEntity add(AddRoleVO vo, String username) {
        existName(vo.getName());

        RoleEntity role = CommonUtils.classCopy(vo, RoleEntity.class);
        role.setCreateTime(LocalDateTime.now());
        role.setCreateBy(username);
        this.save(role);
        return role;
    }

    @Override
    public RoleEntity update(UpdateRoleVO vo, String username) {
        existName(vo.getName());

        RoleEntity role = new RoleEntity();
        role.setId(vo.getId());
        if (!ObjectUtils.isEmpty(vo.getCode())) {
            role.setCode(vo.getCode());
        }
        if (!ObjectUtils.isEmpty(vo.getName())) {
            role.setName(vo.getName());
        }
        if (vo.getStatus() != null) {
            role.setStatus(vo.getStatus());
        }
        if (!ObjectUtils.isEmpty(vo.getRemark())) {
            role.setRemark(vo.getRemark());
        }

        role.setUpdateTime(LocalDateTime.now());
        role.setUpdateBy(username);
        super.updateById(role);

        return role;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean assign(AssignRoleVO vo, String username) {
        // 先删除角色的所有权限，然后再添加新的权限
        QueryWrapper<RolePermissionRelEntity> queryOld = new QueryWrapper<>();
        RolePermissionRelEntity entityOld = new RolePermissionRelEntity();
        entityOld.setRoleId(vo.getRoleId());
        queryOld.setEntity(entityOld);
        rolePermissionRelService.remove(queryOld);

        List<Integer> permissionIds = vo.getPermissionIds();
        if (permissionIds != null) {
            List<RolePermissionRelEntity> list = permissionIds.stream().map(data -> {
                RolePermissionRelEntity entity = new RolePermissionRelEntity();
                entity.setRoleId(vo.getRoleId());
                entity.setPermissionId(data);
                entity.setCreateBy(username);
                entity.setCreateTime(LocalDateTime.now());
                return entity;
            }).collect(Collectors.toList());

            rolePermissionRelService.saveBatch(list);
        }

        return true;
    }

    private void existName(String name) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(name);
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(roleEntity);
        RoleEntity role = super.getOne(queryWrapper);

        if (role != null) {
            throw new RoleNameExistException(1, String.format("角色名称[%s]已存在", name));
        }
    }
}
