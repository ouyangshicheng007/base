package com.yimi.oysc.security;

import com.yimi.oysc.entity.RoleEntity;
import com.yimi.oysc.enumerate.StatusEnum;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/6/25 10:43
 **/
@Data
public class LoginUser implements UserDetails {

    private List<RoleEntity> roles;

//    private List<String> authorities;
//
//    public UserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        super(username, password, authorities);
//    }
//
//    public List<RoleEntity> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<RoleEntity> roles) {
//        this.roles = roles;
//    }


    private String username;

    private String password;

    private StatusEnum status;

    private List<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status == StatusEnum.TRUE;
    }
}
