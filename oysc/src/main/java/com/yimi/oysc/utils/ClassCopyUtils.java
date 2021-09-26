package com.yimi.oysc.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/26 15:07
 **/
@Slf4j
public class ClassCopyUtils {

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


}
