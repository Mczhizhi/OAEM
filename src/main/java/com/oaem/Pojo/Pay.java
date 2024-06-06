package com.oaem.Pojo;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;

@Data
public class Pay {
    /**
     * 员工ID
     */
    private int employeeId;

    /**
     * 支付ID（自增）
     */
    private int payId;

    /**
     * 罚款ID
     */
    private int fineId;

    /**
     * 发送日期
     */
    private Timestamp sendDate;

    /**
     * 绩效工资
     */
    private double meritPay;

    /**
     * 其他支出
     */
    private double otherExpenses;

    /**
     * 创建日期
     */
    private Timestamp createDate;
}
