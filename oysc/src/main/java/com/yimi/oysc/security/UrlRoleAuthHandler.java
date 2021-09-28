package com.yimi.oysc.security;

import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;

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


        if (object instanceof FilterInvocation) {
            FilterInvocation invocation = (FilterInvocation) object;
            System.out.println(invocation.getRequest().getRequestURI());
            System.out.println(JSON.toJSONString(invocation.getRequest().getParameterMap()));

            int result = ACCESS_ABSTAIN;
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//
//        for (ConfigAttribute urlRole : urlRoles) {
//            if (this.supports(urlRole)) {
            // 此处默认值为弃权， 表示只要有一个角色对应上，用户就可以访问链接
            // 如果值改为拒绝，表示必须全部角色包含才能访问
            result = ACCESS_ABSTAIN;




            for (GrantedAuthority userRole : authorities) {
                if (urlRole.getAttribute().equals(userRole.getAuthority())) {
                    return ACCESS_GRANTED;
                }
            }
//            }
//        }
        }



        return result;
    }
}
