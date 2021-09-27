package com.yimi.oysc.exception;

/**
 * 自定义系统异常。
 * 自己抛出的异常请全部继承此类，用以区分是否为自己抛出的异常
 *
 * @Author Davy
 * @Date 2021/9/27 10:58
 **/
public class UserDefineException extends RuntimeException{

    public UserDefineException(String msg) {
        super(msg);
    }

}
