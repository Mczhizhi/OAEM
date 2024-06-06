package com.oaem.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oaem.Pojo.ReportAbsence;
import com.oaem.Pojo.SignInReplenishment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface SignInReplenishmentMapper extends BaseMapper<SignInReplenishment> {

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
            "SELECT  employee.employee_id as id, employee.name as EName, employee.contact_information as phone,sign_in_replenishment.replenishment_id as replenishmentId,\n" +
            "sign_in_replenishment.replenishment_date as startDate,sign_in_replenishment.cause as cause \n" +
            "FROM sign_in_replenishment\n" +
            "LEFT JOIN employee ON employee.employee_id =sign_in_replenishment.employee_id\n" +
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
    List<Map<String, Object>> getAllSignInReplenishment(@Param("limit") int limit, @Param("offset") int offset, @Param("data") String data, @Param("status") Integer status);



    @Update("update holiday set status = #{status} where holiday_id = #{holidayId}")
    int updateStatus(Integer status, Integer holidayId);


    @Select("<script>\n" +
            "SELECT employee.employee_id AS id, employee.name AS EName, employee.contact_information AS phone, sign_in_replenishment.replenishment_id AS replenishmentId,\n" +
            "sign_in_replenishment.replenishment_date AS startDate,  sign_in_replenishment.cause AS cause, sign_in_replenishment.status AS status, sign_in_replenishment.examine AS examine\n" +
            "FROM sign_in_replenishment\n" +
            "LEFT JOIN employee ON employee.employee_id = sign_in_replenishment.replenishment_id\n" +
            "LEFT JOIN department ON department.department_id = employee.department_id\n" +
            "WHERE YEAR(sign_in_replenishment.create_date) = #{year} AND MONTH(sign_in_replenishment.create_date) = #{month} AND sign_in_replenishment.status != '0' \n" +

            "<if test='status != null and status == 1'>\n" + // 如果 status 为 1，则按员工姓名查询
            "AND employee.name LIKE CONCAT('%', #{data}, '%')\n" + // 使用 LIKE 进行模糊查询
            "</if>\n" +
            "<if test='status != null and status == 2'>\n" + // 如果 status 为 2，则按员工编号查询
            "AND employee.employee_id = #{data}\n" + // 使用单等号
            "</if>\n" +

            "LIMIT #{limit} OFFSET #{offset}\n" + // 将 LIMIT 和 OFFSET 放在 </where> 之后
            "</script>")

    List<Map<String, Object>> getAllRenewalHistory(@Param("limit") int limit, @Param("offset") int offset, @Param("data") String data, @Param("status") Integer status, @Param("year") String year, @Param("month") String month);


    @Select("<script>\n" +
            "SELECT COUNT(*)\n" +
            "FROM sign_in_replenishment\n" +
            "LEFT JOIN employee ON employee.employee_id = sign_in_replenishment.replenishment_id\n" +
            "LEFT JOIN department ON department.department_id = employee.department_id\n" +
            "WHERE YEAR(sign_in_replenishment.create_date) = #{year} AND MONTH(sign_in_replenishment.create_date) = #{month} AND sign_in_replenishment.status != '0' \n" +

            "<if test='status != null and status == 1'>\n" +
            " AND employee.name LIKE CONCAT('%', #{data}, '%')\n" +
            "</if>\n" +
            "<if test='status != null and status == 2'>\n" +
            " AND employee.employee_id = #{data}\n" +
            "</if>\n" +

            "</script>")


    int getTotalHistory(@Param("data") String data, @Param("status") Integer status,@Param("year") String year, @Param("month") String month);

}
