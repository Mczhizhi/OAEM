package com.oaem.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oaem.Pojo.Employee;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
    @Select("<script>\n"+
            "SELECT department.name as DName, employee.employee_id as id, employee.name as EName, employee.contact_information as phone\n" +
            "FROM employee\n" +
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
    List<Map<String, Object>> getAllEmployees(@Param("limit") int limit, @Param("offset") int offset, @Param("data") String data, @Param("status") Integer status);

    @Select("<script>\n" +
            "SELECT count(*)\n" +
            "FROM employee\n" +
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
            "SELECT department.name as DName, employee.employee_id as id, employee.name as EName, employee.contact_information as phone,employee.address as address,employee.hiredate as hiredate,login.account as account\n" +
            "FROM employee\n" +
            "LEFT JOIN department ON department.department_id = employee.department_id\n" +
            "LEFT JOIN login ON login.login_id = employee.login_id\n" +
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
    List<Map<String, Object>> getAllEmployeesInformation(@Param("limit") int limit, @Param("offset") int offset, @Param("data") String data, @Param("status") Integer status);

    @Insert("INSERT INTO employee( name, contact_information, department_id, address, hiredate,base_pay,create_date,login_id) VALUES(#{name}, #{contact_information}, #{department_id}, #{address}, #{hiredate},#{base_pay},Now(),#{login})")
    int insertEmployee( String name, String contact_information, Integer department_id, String address, Timestamp hiredate, Double base_pay,Integer login);

    @Select("SELECT name, contact_information as phone , department_id as Did, address, hiredate as date,base_pay as pay FROM employee WHERE employee_id = #{employee_id}")
    Map<String, Object> getEmployeeById(Integer employee_id);

    @Update("update employee set name = #{name}, contact_information = #{phone}, department_id = #{Did}, address = #{address},base_pay = #{pay} where employee_id = #{id}")
    int updateEmployee(String name, String phone, Integer Did, String address,  Double pay, Integer id);

    @Delete("delete from employee where employee_id = #{employee_id}")
    int deleteEmployee(Integer employee_id);

    @Select ("select * from employee,login where login.login_id = employee.login_id and login.login_id = #{login_id}")
    Map<String, Object> getEmployeeByLoginId(Integer login_id);
}

