package com.oaem.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.oaem.Pojo.Department;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

    @Select("<script>\n"+
            "SELECT department.department_id as id, department.name as name, department.principal as principal, employee.contact_information as phone\n" +
            "FROM department\n" +
            "LEFT JOIN employee ON department.principal = employee.employee_id\n" +
            "<where>\n" +
            "<if test='status != null and status == 1'>\n" + // 如果 status 为 1，则按姓名查询
            "AND department.name LIKE CONCAT('%', #{data}, '%')\n" + // 使用 LIKE 进行模糊查询
            "</if>\n" +
            "<if test='status != null and status == 2'>\n" + // 如果 status 为 2，则按编号查询
            "AND department.department_id = #{data}\n" + // 使用单等号
            "</if>\n" +
            "</where>\n" +
            "LIMIT #{limit} OFFSET #{offset}\n" + // 将 LIMIT 和 OFFSET 放在 </where> 之后
            "</script>")

    List<Map<String, Object>> getAllDepartments(@Param("limit") int limit, @Param("offset") int offset, @Param("data") String data, @Param("status") Integer status);


    @Insert("INSERT INTO department(name, department_create_date, principal, description, create_date) \n" +
            "VALUES (#{name}, #{departmentCreateDate},  #{principal}, #{description}, Now());\n")
    int addDepartment(String name, Timestamp departmentCreateDate, String description, Integer principal);

    @Select("<script>\n" +
            "SELECT count(*)\n" +
            "FROM department\n" +
            "LEFT JOIN employee ON department.principal = employee.employee_id\n" + // 移除多余的分号
            "<where>\n" +
            "<if test='status != null and status == 1'>\n" + // 如果 status 为 1，则按姓名查询
            "AND department.name LIKE CONCAT('%', #{data}, '%')\n" + // 使用 LIKE 进行模糊查询
            "</if>\n" +
            "<if test='status != null and status == 2'>\n" + // 如果 status 为 2，则按编号查询
            "AND department.department_id = #{data}\n" + // 使用单等号
            "</if>\n" +
            "</where>\n" +
            "</script>")

    int getTotal(@Param("data") String data, @Param("status") Integer status);



    @Select(
            "SELECT employee.employee_id as id, department.name as DName,employee.name as EName, department.principal as principal, department.department_create_date as createDate,department.description as description\n" +
            "FROM department\n" +
            "LEFT JOIN employee ON department.principal = employee.employee_id\n" +
            "WHERE department.department_id = #{id}\n")

    Map<String, Object> getDepartmentById(Integer id);

    @Update("UPDATE department SET name = #{name}, description = #{description}, principal = #{principal} WHERE department_id = #{id}")
    int updateDepartment(String name, String description, Integer principal, Integer id);


    @Delete("DELETE FROM department WHERE department_id = #{id}")
    int deleteDepartment(Integer id);

    @Select("Select department_id as Did, name as value from department")
    List<Map<String,Object>> QueryDepartment();
}
