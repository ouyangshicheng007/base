package com.yimi.oysc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yimi.oysc.entity.PermissionEntity;
import com.yimi.oysc.entity.RoleEntity;
import com.yimi.oysc.entity.RolePermissionRelEntity;
import com.yimi.oysc.exception.RoleNameExistException;
import com.yimi.oysc.exception.UserDefineException;
import com.yimi.oysc.mapper.PermissionMapper;
import com.yimi.oysc.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yimi.oysc.service.IRolePermissionRelService;
import com.yimi.oysc.service.IRoleService;
import com.yimi.oysc.utils.CommonUtils;
import com.yimi.oysc.vo.add.AddPermissionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Davy
 * @since 2021-09-26
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements IPermissionService {

    @Autowired
    private IRolePermissionRelService rolePermissionRelService;

    @Autowired
    private IRoleService roleService;

    @Override
    public PermissionEntity add(AddPermissionVO vo, String username) {
        existName(vo.getName());

        PermissionEntity entity = CommonUtils.classCopy(vo, PermissionEntity.class);
        entity.setCreateTime(LocalDateTime.now());
        entity.setCreateBy(username);
        super.save(entity);
        return entity;
    }

    @Override
    public List<PermissionEntity> listByRoleId(Integer roleId) {
        // 判断如果是超级管理员，直接拥有全部资源权限
        RoleEntity roleEntity = roleService.getById(roleId);
        if ("ROLE_SUPER_ADMIN".equals(roleEntity.getCode())) {
            return super.baseMapper.selectList(new QueryWrapper<>());
        } else {
            return super.baseMapper.listByRoleId(roleId);
        }
    }

    @Override
    public List<PermissionEntity> listByUserId(Integer userId) {
        List<RoleEntity> roles = roleService.findRolesByUserId(userId);
        Optional<RoleEntity> superAdmin = roles.stream().filter(data -> data.getCode().equals("ROLE_SUPER_ADMIN")).findAny();
        if (superAdmin.isPresent()) {
            return super.baseMapper.selectList(new QueryWrapper<>());
        }

        return super.baseMapper.listByUserId(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deletePermission(Integer permissionId) {
        QueryWrapper<RolePermissionRelEntity> queryRolePermission = new QueryWrapper<>();
        RolePermissionRelEntity rolePermissionRel = new RolePermissionRelEntity();
        rolePermissionRel.setPermissionId(permissionId);
        queryRolePermission.setEntity(rolePermissionRel);
        rolePermissionRelService.remove(queryRolePermission);

        super.baseMapper.deleteById(permissionId);
        return true;
    }


    private void existName(String name) {
        PermissionEntity entity = new PermissionEntity();
        entity.setName(name);
        QueryWrapper<PermissionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(entity);
        PermissionEntity permission = super.getOne(queryWrapper);

        if (permission != null) {
            throw new UserDefineException(1, String.format("权限资源名称[%s]已存在", name));
        }
    }
}
