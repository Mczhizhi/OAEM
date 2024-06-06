package com.oaem.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oaem.Pojo.EventRelease;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Mapper
public interface EvenReleaseMapper extends BaseMapper<EventRelease> {
    @Insert("insert into event_release (department_id, publish_date,publish_person,type,create_date) " +
            "values (#{department_id}, #{publish_date},#{publish_person},#{type},Now())")
    int insert(Integer department_id, Timestamp publish_date, Integer publish_person, Integer type);
}
