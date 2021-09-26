package com.yimi.oysc.vo.add;

import com.yimi.oysc.enumerate.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/26 15:03
 **/
@Data
@ApiModel("添加角色参数")
public class AddRoleVO {

    @NotNull(message = "角色名称不能为空")
    @ApiModelProperty("角色名称")
    private String name;

    @NotNull(message = "角色编码不能为空")
    @ApiModelProperty("角色编码")
    private String code;

    @ApiModelProperty("角色备注")
    private String remark;

    @ApiModelProperty("角色状态")
    private StatusEnum status = StatusEnum.TRUE;
}
