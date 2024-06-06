package com.oaem.Pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TurnoverSituation {
    /**
     * 员工ID
     */
    private int employeeId;

    /**
     * 离职记录ID
     */
    private int dimissionId;

    /**
     * 部门ID
     */
    private int departmentId;

    /**
     * 离职日期
     */
    private Timestamp dimissionDate;

    /**
     * 入职日期
     */
    private Timestamp entryDate;

    /**
     * 创建日期
     */
    private Timestamp createDate;
}