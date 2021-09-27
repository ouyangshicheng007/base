package com.yimi.oysc.vo.select;

import com.yimi.oysc.enumerate.SexEnum;
import com.yimi.oysc.enumerate.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/26 14:00
 **/
@Data
@ApiModel("查询用户条件")
public class SelectUserVO {

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("用户性别")
    private SexEnum sex;

    @ApiModelProperty("用户状态")
    private StatusEnum status;
}
