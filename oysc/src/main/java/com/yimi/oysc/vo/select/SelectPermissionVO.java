package com.yimi.oysc.vo.select;

import com.yimi.oysc.enumerate.PermissionTypeEnum;
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
public class SelectPermissionVO {

    @ApiModelProperty("资源名称")
    private String name;

    @ApiModelProperty("资源类型")
    private PermissionTypeEnum type;

    @ApiModelProperty("资源状态")
    private StatusEnum status;
}
