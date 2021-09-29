package com.yimi.oysc.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yimi.oysc.exception.ValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/28 15:40
 **/
@Component
public class LoginLogoutHandler implements AuthenticationFailureHandler, AuthenticationSuccessHandler, LogoutSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     *  登录失败时
     * @Author: Davy
     * @Date: 2021/9/28 15:45
     **/
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter out = response.getWriter();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",401);
        if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
            map.put("message","用户名或密码错误");
        } else if (ex instanceof DisabledException) {
            map.put("message","账户被禁用");
        } else if (ex.getCause() instanceof ValidateException) {
            map.put("message","账户不允许登录");
        } else {
            map.put("message","登录失败!");
        }
        out.write(objectMapper.writeValueAsString(map));
        out.flush();
        out.close();
    }

    /**
     *  登录成功时
     * @Author: Davy
     * @Date: 2021/9/28 15:45
    **/
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",200);
        map.put("message","登录成功");
        map.put("data",authentication);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(objectMapper.writeValueAsString(map));
        out.flush();
        out.close();
    }

    /**
     *  登出成功时
     * @Author: Davy
     * @Date: 2021/9/28 15:48
    **/
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",200);
        map.put("message","登出成功");
        map.put("data",authentication);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(objectMapper.writeValueAsString(map));
        out.flush();
        out.close();
    }
}
