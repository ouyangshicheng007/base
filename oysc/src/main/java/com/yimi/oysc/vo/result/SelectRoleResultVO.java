package com.yimi.oysc.vo.result;

import com.yimi.oysc.enumerate.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Auto-Generated
 *
 * @Author Davy
 * @Date 2021/9/26 13:45
 **/
@Data
public class SelectRoleResultVO {

    private String name;

    private String code;

    private StatusEnum status;

    private LocalDateTime createTime;

    private String createBy;

}
