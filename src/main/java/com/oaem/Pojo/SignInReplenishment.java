package com.oaem.Pojo;

import lombok.Data;

import java.util.Date;

@Data
public class SignInReplenishment {
    // 员工ID
    private int employeeId;

    // 补签ID
    private int replenishmentId;

    // 补签日期
    private Date replenishmentDate;

    // 补签原因
    private String cause;

    // 补签状态
    private int status;

    // 记录创建日期
    private Date createDate;

    // 审核人ID
    private int examine;
}
