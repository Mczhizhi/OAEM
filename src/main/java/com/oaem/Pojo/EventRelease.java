package com.oaem.Pojo;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class EventRelease {
    /**
     * 活动ID（自增）
     */
    private int activityId;

    /**
     * 部门ID
     */
    private int departmentId;

    /**
     * 发布日期
     */
    private Timestamp publishDate;

    /**
     * 发布人
     */
    private int publishPerson;

    /**
     * 创建日期
     */
    private Timestamp createDate;

    /**
     * 类型
     */
    private int type;
}