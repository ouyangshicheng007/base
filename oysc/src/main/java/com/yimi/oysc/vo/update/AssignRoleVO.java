package com.yimi.oysc.vo.update;

import com.yimi.oysc.enumerate.StatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/26 15:03
 **/
@Data
public class AssignRoleVO {

    @NotNull(message = "修改ID不能为空")
    @ApiModelProperty("角色ID")
    private Integer roleId;

    @ApiModelProperty("权限资源id列表")
    private List<Integer> permissionIds ;

}
