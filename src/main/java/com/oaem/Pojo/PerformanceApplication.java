package com.oaem.Pojo;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

@Data
public class PerformanceApplication {
    /**
     * 员工ID
     */
    private int employeeId;

    /**
     * 绩效申请ID（自增）
     */
    private int performanceId;

    /**
     * 申请金额
     */
    private double appliedAmount;

    /**
     * 申请日期
     */
    private Timestamp appliedDate;

    /**
     * 状态
     */
    private int status;

    /**
     * 创建日期
     */
    private Timestamp createDate;

    /**
     * 描述
     */
    private String description;

    /**
     * 审核人
     */
    private int examiner;
}
