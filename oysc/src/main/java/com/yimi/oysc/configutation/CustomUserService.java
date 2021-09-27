package com.yimi.oysc.configutation;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yimi.oysc.entity.UserEntity;
import com.yimi.oysc.exception.ValidateException;
import com.yimi.oysc.service.IUserService;
import com.yimi.oysc.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

        List<RoleVO> list = new ArrayList<>();
        RoleVO role1 = new RoleVO();
        role1.setName("普通管理员");
        role1.setEnable(true);

        RoleVO role2 = new RoleVO();
        role2.setName("部门经理");
        role2.setEnable(true);

        list.add(role1);
        list.add(role2);
        user.setRoles(list);

        return user;
    }
}
