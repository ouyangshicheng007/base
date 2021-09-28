package com.yimi.oysc.utils;

import com.yimi.oysc.security.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/26 15:07
 **/
@Slf4j
public class CommonUtils {

    public static <T, R> R classCopy(T source, Class<R> target) {
        try {
            R instance = target.newInstance();
            BeanUtils.copyProperties(source, instance);
            return instance;
        } catch (Exception e) {
            log.error("属性复制异常", e);
            throw new RuntimeException(e);
        }
    }


    public static LoginUser getLoginUserDetail() {
        return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String getLoginUsername() {
        return getLoginUserDetail().getUsername();
    }

    /**
     * 获取用户真实IP地址
     * @param request
     * @return
     */
    public static String getReqIpAddr(HttpServletRequest request) {
        String ip = request.getHeader( "X-Forwarded-For" );
        if (!ObjectUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0,index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (!ObjectUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }


}
