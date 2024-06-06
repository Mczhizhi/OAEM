package com.oaem.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oaem.Pojo.PerformanceApplication;
import com.oaem.Pojo.ReportAbsence;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Mapper
public interface PerformanceApplicationMapper extends BaseMapper<PerformanceApplication> {

    @Select("<script>\n" +
            "SELECT count(*)\n" +
            "FROM performance_application\n" +
            "LEFT JOIN employee ON employee.employee_id =performance_application.employee_id\n" +
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
            "SELECT  employee.employee_id as id, employee.name as EName, employee.contact_information as phone,performance_application.performance_id as performanceId,\n" +
            "performance_application.applied_date as startDate,performance_application.applied_amount as amount,performance_application.description as cause \n" +
            "FROM performance_application\n" +
            "LEFT JOIN employee ON employee.employee_id =performance_application.employee_id\n" +
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
    List<Map<String, Object>> getAllPerformanceApplication(@Param("limit") int limit, @Param("offset") int offset, @Param("data") String data, @Param("status") Integer status);



    @Update("update performance_application set status = #{status} where performance_application_id = #{performance_applicationId}")
    int updateStatus(Integer status, Integer performance_applicationId);


    @Select("<script>\n" +
            "SELECT employee.employee_id AS id, employee.name AS EName, employee.contact_information AS phone, performance_application.performance_id AS performanceId,\n" +
            "performance_application.applied_date AS startDate,  performance_application.description AS description, performance_application.status AS status, performance_application.examine AS examine\n" +
            "FROM performance_application\n" +
            "LEFT JOIN employee ON employee.employee_id = performance_application.performance_id\n" +
            "LEFT JOIN department ON department.department_id = employee.department_id\n" +
            "WHERE YEAR(performance_application.create_date) = #{year} AND MONTH(performance_application.create_date) = #{month} AND performance_application.status != '0' \n" +

            "<if test='status != null and status == 1'>\n" + // 如果 status 为 1，则按员工姓名查询
            "AND employee.name LIKE CONCAT('%', #{data}, '%')\n" + // 使用 LIKE 进行模糊查询
            "</if>\n" +
            "<if test='status != null and status == 2'>\n" + // 如果 status 为 2，则按员工编号查询
            "AND employee.employee_id = #{data}\n" + // 使用单等号
            "</if>\n" +

            "LIMIT #{limit} OFFSET #{offset}\n" + // 将 LIMIT 和 OFFSET 放在 </where> 之后
            "</script>")

    List<Map<String, Object>> getAllPerformanceHistory(@Param("limit") int limit, @Param("offset") int offset, @Param("data") String data, @Param("status") Integer status, @Param("year") String year, @Param("month") String month);


    @Select("<script>\n" +
            "SELECT COUNT(*)\n" +
            "FROM performance_application\n" +
            "LEFT JOIN employee ON employee.employee_id = performance_application.performance_id\n" +
            "LEFT JOIN department ON department.department_id = employee.department_id\n" +
            "WHERE YEAR(performance_application.create_date) = #{year} AND MONTH(performance_application.create_date) = #{month} AND performance_application.status != '0' \n" +

            "<if test='status != null and status == 1'>\n" +
            " AND employee.name LIKE CONCAT('%', #{data}, '%')\n" +
            "</if>\n" +
            "<if test='status != null and status == 2'>\n" +
            " AND employee.employee_id = #{data}\n" +
            "</if>\n" +

            "</script>")


    int getTotalHistory(@Param("data") String data, @Param("status") Integer status,@Param("year") String year, @Param("month") String month);


    @Insert("insert into performance_application(employee_id,applied_amount,applied_date,description,status,examine，create_date) " +
            "values(#{employeeId},#{appliedAmount},#{appliedDate},#{description},0,NULL,Now())")
    int insertEmployeeId(Integer employeeId, Double appliedAmount, Timestamp appliedDate, String description);
}
