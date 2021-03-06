package com.yimi.oysc.vo.add;

import com.yimi.oysc.enumerate.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @ApiModelProperty(value = "修改日期", example = "2021-01-01 12:00:00")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "测试日期", example = "2021-01-01")
    private LocalDate testDate;
}
