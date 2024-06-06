package com.oaem.Pojo;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

@Data
public class Holiday {
    /**
     * 员工ID
     */
    private int employeeId;

    /**
     * 假期ID（自增）
     */
    private int holidayId;

    /**
     * 申请日期
     */
    private Timestamp applicationDate;

    /**
     * 结束日期
     */
    private Timestamp endDate;

    /**
     * 假期类型
     */
    private String type;

    /**
     * 创建日期
     */
    private Timestamp createDate;

    /**
     * 状态
     */
    private int status;

    /**
     * 假期原因
     */
    private String cause;

    /**
     * 审核人
     */
    private int examiner;
}
