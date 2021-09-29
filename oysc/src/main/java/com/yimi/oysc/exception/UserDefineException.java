package com.yimi.oysc.exception;

import lombok.Data;

/**
 * 自定义系统异常。
 * 自己抛出的异常请全部继承此类，用以区分是否为自己抛出的异常
 *
 * @Author Davy
 * @Date 2021/9/27 10:58
 **/
@Data
public class UserDefineException extends RuntimeException{

    private Integer errCode;

    private String errMsg;

    public UserDefineException(Integer errCode, String msg) {
        super(msg);
        this.errCode = errCode;
        this.errMsg = msg;
    }

}
