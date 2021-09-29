package com.yimi.oysc.enumerate;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/27 9:21
 **/
public enum SexEnum {

    /**
     *  男性
    **/
    MALE(1),

    /**
     *  女性
     **/
    FEMALE(0);


    @EnumValue
    int value;

    SexEnum(int value) {
        this.value = value;
    }

}
