package com.yimi.oysc.configutation;

import com.yimi.oysc.entity.RoleEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/6/25 10:43
 **/
public class UserDetail extends User {

    private List<RoleEntity> roles;

    private List<String> authorities;

    public UserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
}
