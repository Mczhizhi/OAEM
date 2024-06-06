package com.oaem.Pojo;

import lombok.Data;

import java.sql.Timestamp;


@Data
public class Login {
    // 登录ID，自增长
    private int loginId;

    // 账号，不可为空
    private String account;

    // 密码，可为空
    private String password;



    // 权限
    private int authority;

    // 创建日期
    private Timestamp createDate;
}
