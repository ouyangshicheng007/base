package com.yimi.oysc.vo.update;

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
@ApiModel("修改角色参数")
public class UpdateRoleVO {

    @NotNull(message = "修改ID不能为空")
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色编码")
    private String code;

    @ApiModelProperty("角色状态")
    private StatusEnum status;

    @ApiModelProperty("角色备注")
    private String remark;
}
