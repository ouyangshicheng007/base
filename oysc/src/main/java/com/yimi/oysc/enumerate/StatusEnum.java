package com.yimi.oysc.enumerate;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/24 18:04
 **/
public enum StatusEnum {

    TRUE(1),


    FALSE(0);


    @EnumValue
    int value;

    StatusEnum(int value) {
        this.value = value;
    }
}
