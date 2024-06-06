package com.oaem.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oaem.Pojo.Holiday;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Mapper
public interface HolidayMapper extends BaseMapper<Holiday> {


    @Select("<script>\n" +
            "SELECT count(*)\n" +
            "FROM holiday\n" +
            "LEFT JOIN employee ON employee.employee_id =holiday.employee_id\n" +
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
            "SELECT  employee.employee_id as id, employee.name as EName, employee.contact_information as phone,holiday.holiday_id as holidayId,\n" +
            "holiday.application_date as startDate,holiday.end_date as endDate,holiday.type as type,holiday.cause as cause \n" +
            "FROM holiday\n" +
            "LEFT JOIN employee ON employee.employee_id =holiday.employee_id\n" +
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
    List<Map<String, Object>> getAllHolidays(@Param("limit") int limit, @Param("offset") int offset, @Param("data") String data, @Param("status") Integer status);



    @Update("update holiday set status = #{status} where holiday_id = #{holidayId}")
    int updateStatus(Integer status, Integer holidayId);


    @Select("<script>\n" +
            "SELECT employee.employee_id AS id, employee.name AS EName, employee.contact_information AS phone, holiday.holiday_id AS holidayId,\n" +
            "holiday.application_date AS startDate, holiday.end_date AS endDate, holiday.type AS type, holiday.cause AS cause, holiday.status AS status, holiday.examine AS examine\n" +
            "FROM holiday\n" +
            "LEFT JOIN employee ON employee.employee_id = holiday.employee_id\n" +
            "LEFT JOIN department ON department.department_id = employee.department_id\n" +
            "WHERE YEAR(holiday.create_date) = #{year} AND MONTH(holiday.create_date) = #{month} AND holiday.status != '0' \n" +

            "<if test='status != null and status == 1'>\n" + // 如果 status 为 1，则按员工姓名查询
            "AND employee.name LIKE CONCAT('%', #{data}, '%')\n" + // 使用 LIKE 进行模糊查询
            "</if>\n" +
            "<if test='status != null and status == 2'>\n" + // 如果 status 为 2，则按员工编号查询
            "AND employee.employee_id = #{data}\n" + // 使用单等号
            "</if>\n" +

            "LIMIT #{limit} OFFSET #{offset}\n" + // 将 LIMIT 和 OFFSET 放在 </where> 之后
            "</script>")

    List<Map<String, Object>> getAllHolidaysHistory(@Param("limit") int limit, @Param("offset") int offset, @Param("data") String data, @Param("status") Integer status, @Param("year") String year, @Param("month") String month);


    @Select("<script>\n" +
            "SELECT COUNT(*)\n" +
            "FROM holiday\n" +
            "LEFT JOIN employee ON employee.employee_id = holiday.employee_id\n" +
            "LEFT JOIN department ON department.department_id = employee.department_id\n" +
            "WHERE YEAR(holiday.create_date) = #{year} AND MONTH(holiday.create_date) = #{month} AND holiday.status != '0' \n" +

            "<if test='status != null and status == 1'>\n" +
            " AND employee.name LIKE CONCAT('%', #{data}, '%')\n" +
            "</if>\n" +
            "<if test='status != null and status == 2'>\n" +
            " AND employee.employee_id = #{data}\n" +
            "</if>\n" +

            "</script>")


    int getTotalHistory(@Param("data") String data, @Param("status") Integer status,@Param("year") String year, @Param("month") String month);

    @Insert("insert into holiday(employee_id, application_date, end_date, type, cause, status, examine) " +
            "values(#{employeeId}, #{applicationDate}, #{endDate}, #{type}, #{cause}, 0, NULL)")

    int addEmployeeHoliday(Integer employeeId, Timestamp applicationDate, Timestamp endDate, int type, String cause);

}
