package com.oaem.Pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class Department {
    // 部门ID
    private Integer departmentId;

    // 部门名称
    private String name;

    // 部门创建日期
    private Date departmentCreateDate;

    // 记录创建日期
    private Date createDate;

    // 部门描述
    private String description;

    // 部门状态
    private Integer status;

    // 部门负责人ID
    private Integer principal;
}
