package com.yimi.oysc.vo.add;

import com.baomidou.mybatisplus.annotation.TableField;
import com.yimi.oysc.enumerate.SexEnum;
import com.yimi.oysc.enumerate.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/27 9:23
 **/
@ApiModel("添加用户参数")
@Data
public class AddUserVO {

    @ApiModelProperty("用户名")
    @NotNull(message = "用户名不能为空")
    private String username;

    @ApiModelProperty("密码")
    @NotNull(message = "密码不能为空")
    private String password;

    /**
     * 1:启用，0：禁用
     */
    @ApiModelProperty("状态")
    @NotNull(message = "状态不能为空")
    private StatusEnum status;

    /**
     * 1:男性，0：女性
     */
    @TableField("SEX")
    @NotNull(message = "性别不能为空")
    private SexEnum sex;
}
