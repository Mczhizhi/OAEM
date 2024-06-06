package com.oaem.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oaem.Pojo.Login;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface LoginMapper extends BaseMapper<Login> {

    @Insert("insert into login (account, password,authority,create_date) values (#{account}, #{password},#{authority},Now())")
    int insertLogin(String account, String password, Integer authority);
    @Select("select count(*) from login where account = #{account} and password = #{password}")
    int Login(String account, String password);
    @Select("select login_id from login where account = #{account}")
    int getLoginId(String account);
    @Select("select * from login where account= #{account}")
    Login getLogin(String account);
    @Select("select employee_id as id, name, contact_information as phone , department_id as Did, address, hiredate as date,base_pay as pay from login,employee where account = #{account} and login.login_id = employee.login_id")
    Map<String, Object> getLoginByAccount(String account);
}
