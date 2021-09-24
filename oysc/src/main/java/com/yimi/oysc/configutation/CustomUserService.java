package com.yimi.oysc.configutation;

import com.yimi.oysc.exception.ValidateException;
import com.yimi.oysc.vo.RoleVO;
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.equals("root")) {
            throw new ValidateException("错误");
        }

        UserDetail user = new UserDetail("admin",new BCryptPasswordEncoder().encode("123456"), new ArrayList<>());

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
