package com.yimi.oysc.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 *
 * 角色、权限 路由处理
 *
 * @Author Davy
 * @Date 2021/9/28 16:42
 **/
public class UrlRoleAuthHandler implements AccessDecisionVoter<Object> {

    @Override
    public boolean supports(ConfigAttribute attribute) {
        if (attribute.getAttribute() == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * ACCESS_GRANTED  - 同意
     * ACCESS_DENIED   - 拒绝
     * ACCESS_ABSTAIN  - 弃权
     *
     * @Author: Davy
     * @Date: 2021/9/28 16:43
    **/
    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> urlRoles) {
        if (authentication == null) {
            return ACCESS_DENIED;
        }

        int result = ACCESS_ABSTAIN;
        if (object instanceof FilterInvocation) {
            FilterInvocation invocation = (FilterInvocation) object;
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            result = ACCESS_ABSTAIN;

            for (GrantedAuthority userRole : authorities) {
                Pattern pattern = Pattern.compile(userRole.getAuthority());
                if (pattern.matcher(invocation.getHttpRequest().getRequestURI()).matches()) {
                    return ACCESS_GRANTED;
                }
            }
        }

        return result;
    }
}
