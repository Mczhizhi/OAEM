package com.oaem.Pojo;

import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

@Data
public class SignIn {
    /**
     * 员工ID
     */
    private int employeeId;

    /**
     * 签到记录ID（自增）
     */
    private int signId;

    /**
     * 签到日期
     */
    private Timestamp signDate;

    /**
     * 签到类型
     */
    private int type;

    /**
     * 创建日期
     */
    private Timestamp createDate;
}
