package com.oaem.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oaem.Pojo.ReportAbsence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportAbsenceMapper extends BaseMapper<ReportAbsence> {

    @Select("<script>\n" +
            "SELECT count(*)\n" +
            "FROM report_absence\n" +
            "LEFT JOIN employee ON employee.employee_id =report_absence.employee_id\n" +
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
            "SELECT  employee.employee_id as id, employee.name as EName, employee.contact_information as phone,report_absence.absence_id as absenceId,\n" +
            "report_absence.applied_date as startDate,report_absence.cause as cause \n" +
            "FROM report_absence\n" +
            "LEFT JOIN employee ON employee.employee_id =report_absence.employee_id\n" +
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
    List<Map<String, Object>> getAllReportAbsence(@Param("limit") int limit, @Param("offset") int offset, @Param("data") String data, @Param("status") Integer status);



    @Update("update report_absence set status = #{status} where report_absence_id = #{report_absenceId}")
    int updateStatus(Integer status, Integer report_absenceId);

    @Select("<script>\n" +
            "SELECT employee.employee_id AS id, employee.name AS EName, employee.contact_information AS phone, report_absence.absence_id AS absenceId,\n" +
            "report_absence.applied_date AS startDate,  report_absence.cause AS cause, report_absence.status AS status, report_absence.examine AS examine\n" +
            "FROM report_absence\n" +
            "LEFT JOIN employee ON employee.employee_id = report_absence.employee_id\n" +
            "LEFT JOIN department ON department.department_id = employee.department_id\n" +
            "WHERE YEAR(report_absence.create_date) = #{year} AND MONTH(report_absence.create_date) = #{month} AND report_absence.status != '0' \n" +

            "<if test='status != null and status == 1'>\n" + // 如果 status 为 1，则按员工姓名查询
            "AND employee.name LIKE CONCAT('%', #{data}, '%')\n" + // 使用 LIKE 进行模糊查询
            "</if>\n" +
            "<if test='status != null and status == 2'>\n" + // 如果 status 为 2，则按员工编号查询
            "AND employee.employee_id = #{data}\n" + // 使用单等号
            "</if>\n" +

            "LIMIT #{limit} OFFSET #{offset}\n" + // 将 LIMIT 和 OFFSET 放在 </where> 之后
            "</script>")

    List<Map<String, Object>> getAllAbsenceHistory(@Param("limit") int limit, @Param("offset") int offset, @Param("data") String data, @Param("status") Integer status, @Param("year") String year, @Param("month") String month);


    @Select("<script>\n" +
            "SELECT COUNT(*)\n" +
            "FROM report_absence\n" +
            "LEFT JOIN employee ON employee.employee_id = report_absence.employee_id\n" +
            "LEFT JOIN department ON department.department_id = employee.department_id\n" +
            "WHERE YEAR(report_absence.create_date) = #{year} AND MONTH(report_absence.create_date) = #{month} AND report_absence.status != '0' \n" +

            "<if test='status != null and status == 1'>\n" +
            " AND employee.name LIKE CONCAT('%', #{data}, '%')\n" +
            "</if>\n" +
            "<if test='status != null and status == 2'>\n" +
            " AND employee.employee_id = #{data}\n" +
            "</if>\n" +

            "</script>")


    int getTotalHistory(@Param("data") String data, @Param("status") Integer status,@Param("year") String year, @Param("month") String month);

}
