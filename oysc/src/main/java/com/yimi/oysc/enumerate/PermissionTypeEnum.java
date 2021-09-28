package com.yimi.oysc.enumerate;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/24 17:57
 **/
public enum PermissionTypeEnum {

    URL("URL"),

    MENU("MENU"),

    BUTTON("BUTTON"),

    FIELD("FIELD"),

;

    @EnumValue
    String value;

    PermissionTypeEnum(String value) {
        this.value = value;
    }
}
