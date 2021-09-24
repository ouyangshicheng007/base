package com.yimi.oysc.configutation;

import com.yimi.oysc.vo.RoleVO;
import lombok.Data;
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

    private List<RoleVO> roles;

    public UserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public List<RoleVO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleVO> roles) {
        this.roles = roles;
    }
}
