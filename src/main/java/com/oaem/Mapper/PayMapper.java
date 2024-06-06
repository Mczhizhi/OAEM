package com.oaem.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oaem.Pojo.Pay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface PayMapper extends BaseMapper<Pay> {
    @Select("<script>\n" +
            "SELECT count(*)\n" +
            "FROM pay\n" +
            "LEFT JOIN employee ON employee.employee_id =pay.employee_id\n" +
            "LEFT JOIN department ON department.department_id = employee.department_id\n" +
            "<where>\n" +
            "<if test='status != null and status == 1'>\n" + // 如果 status 为 1，则按员工姓名查询
            "AND employee.name LIKE CONCAT('%', #{data}, '%')\n" + // 使用 LIKE 进行模糊查询
            "</if>\n" +
            "<if test='status != null and status == 2'>\n" + // 如果 status 为 2，则按员工编号查询
            "AND employee.employee_id = #{data}\n" + // 使用单等号
            "</if>\n" +
            "</where>\n" +
            "</script>")
    int getTotal(@Param("data") String data, @Param("status") Integer status);


    @Select("<script>\n"+
            "SELECT  employee.employee_id as id, employee.name as EName, employee.contact_information as phone,pay.pay_id as payId,\n" +
            "pay.send_date as startDate,pay.merit_pay as merit,pay.other_expenses as other,employee.base_pay as base \n" +
            "FROM pay\n" +
            "LEFT JOIN employee ON employee.employee_id =pay.employee_id\n" +
            "LEFT JOIN department ON department.department_id = employee.department_id\n" +
            "<where>\n" +
            "<if test='status != null and status == 1'>\n" + // 如果 status 为 1，则按员工姓名查询
            "AND employee.name LIKE CONCAT('%', #{data}, '%')\n" + // 使用 LIKE 进行模糊查询
            "</if>\n" +
            "<if test='status != null and status == 2'>\n" + // 如果 status 为 2，则按员工编号查询
            "AND employee.employee_id = #{data}\n" + // 使用单等号
            "</if>\n" +
            "</where>\n" +
            "LIMIT #{limit} OFFSET #{offset}\n" + // 将 LIMIT 和 OFFSET 放在 </where> 之后
            "</script>")
    List<Map<String, Object>> getAllPay(@Param("limit") int limit, @Param("offset") int offset, @Param("data") String data, @Param("status") Integer status);

}
