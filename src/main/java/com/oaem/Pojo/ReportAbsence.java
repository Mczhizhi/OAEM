package com.oaem.Pojo;

import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

@Data
public class ReportAbsence {
    /**
     * 员工ID
     */
    private int employeeId;

    /**
     * 缺勤记录ID（自增）
     */
    private int absenceId;

    /**
     * 假期ID
     */
    private int holidayId;

    /**
     * 申请日期
     */
    private Timestamp appliedDate;

    /**
     * 缺勤原因
     */
    private String cause;

    /**
     * 创建日期
     */
    private Timestamp createDate;

    /**
     * 状态
     */
    private int status;

    /**
     * 审核人
     */
    private int examiner;
}
