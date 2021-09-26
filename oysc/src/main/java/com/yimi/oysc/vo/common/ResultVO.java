package com.yimi.oysc.vo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/26 14:55
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {

    private Integer status = 0;

    private String errMsg;

    private T data;

    public boolean isSuccess() {
        return this.status == 0;
    }

    public static <T> ResultVO<T> successResult(T data) {

        return new ResultVO<T>(0, null, data);
    }

    public static <T> ResultVO<T> errorResult(int errCode, String errMsg) {

        return new ResultVO<T>(errCode, errMsg, null);
    }

}
