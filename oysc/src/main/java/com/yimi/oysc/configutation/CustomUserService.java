package com.yimi.oysc.configutation;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yimi.oysc.entity.RoleEntity;
import com.yimi.oysc.entity.UserEntity;
import com.yimi.oysc.service.IPermissionService;
import com.yimi.oysc.service.IRoleService;
import com.yimi.oysc.service.IUserRoleRelService;
import com.yimi.oysc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/6/24 18:41
 **/
@Component
public class CustomUserService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserRoleRelService userRoleRelService;

    @Autowired
    private IPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        UserEntity userQuery = new UserEntity();
        userQuery.setUsername(username);
        queryWrapper.setEntity(userQuery);
        UserEntity userEntity = userService.getOne(queryWrapper);

        if (userEntity == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }

        UserDetail user = new UserDetail(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());


        // 查询用户有哪些角色
        List<RoleEntity> roles = roleService.findRolesByUserId(userEntity.getId());
        user.setRoles(roles);
        return user;
    }
}
