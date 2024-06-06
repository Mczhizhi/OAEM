package com.oaem.Pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Fine {
    /**
     * 签到ID
     */
    private int signId;

    /**
     * 员工ID
     */
    private int employeeId;

    /**
     * 罚款ID（自增）
     */
    private int fineId;

    /**
     * 罚款原因
     */
    private String cause;

    /**
     * 添加人
     */
    private int addPerson;

    /**
     * 创建日期
     */
    private Timestamp createDate;

    /**
     * 添加日期
     */
    private Timestamp addDate;
}