package com.oaem.Pojo;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class Employee {
    private int employee_id;            // 员工ID
    private int department_id;          // 部门ID
    private int login_in;               // 登录ID
    private String name;                // 姓名
    private String contact_information; // 联系信息
    private String address;             // 地址
    private Timestamp hiredate;              // 入职日期
    private Date create_date;           // 创建日期
    private double base_pay;            // 基本工资
}
