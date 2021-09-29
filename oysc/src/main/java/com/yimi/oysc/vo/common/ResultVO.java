package com.yimi.oysc.vo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("接口结果封装对象")
public class ResultVO<T> {

    @ApiModelProperty("请求状态，0为正常，其他为异常")
    private Integer status = 0;

    @ApiModelProperty("错误消息")
    private String errMsg;

    @ApiModelProperty("接口返回数据对象")
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
