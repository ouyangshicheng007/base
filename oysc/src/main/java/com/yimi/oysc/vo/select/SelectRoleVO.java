package com.yimi.oysc.vo.select;

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
@ApiModel("查询角色条件")
public class SelectRoleVO {

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色编码")
    private String code;

    @ApiModelProperty("角色状态")
    private StatusEnum status;
}
