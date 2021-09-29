package com.yimi.oysc.enumerate;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/26 18:07
 **/
public enum OperationLogTypeEnum {

    SELECT("SELECT"),

    UPDATE("UPDATE"),

    DELETE("DELETE"),

    ADD("ADD"),

;
    @EnumValue
    String value;

    OperationLogTypeEnum(String value) {
        this.value = value;
    }
}
