package com.oaem.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oaem.Pojo.SignIn;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Mapper
public interface SignInMapper extends BaseMapper<SignIn> {
    @Select("<script>\n" +
            "SELECT employee.employee_id AS id, employee.name AS EName, employee.contact_information AS phone, sign_in.sign_id AS signId,\n" +
            "sign_in.sign_date AS startDate,sign_in.type as type\n" +
            "FROM sign_in\n" +
            "LEFT JOIN employee ON employee.employee_id = sign_in.publish\n" +
            "LEFT JOIN department ON department.department_id = employee.department_id\n" +
            "WHERE YEAR(sign_in.create_date) = #{year} AND MONTH(sign_in.create_date) = #{month}  AND " +
            "DAY(sign_in.create_date)= #{day}  \n" +

            "<if test='status != null and status == 1'>\n" + // 如果 status 为 1，则按员工姓名查询
            "AND employee.name LIKE CONCAT('%', #{data}, '%')\n" + // 使用 LIKE 进行模糊查询
            "</if>\n" +
            "<if test='status != null and status == 2'>\n" + // 如果 status 为 2，则按员工编号查询
            "AND employee.employee_id = #{data}\n" + // 使用单等号
            "</if>\n" +

            "LIMIT #{limit} OFFSET #{offset}\n" + // 将 LIMIT 和 OFFSET 放在 </where> 之后
            "</script>")
    List<Map<String, Object>> getAllSignInHistory(@Param("limit") int limit, @Param("offset") int offset, @Param("data") String data, @Param("status") Integer status, @Param("year") String year, @Param("month") String month,@Param("day") String day);


    @Select("<script>\n" +
            "SELECT COUNT(*)\n" +
            "FROM sign_in\n" +
            "LEFT JOIN employee ON employee.employee_id = sign_in.employee_id\n" +
            "LEFT JOIN department ON department.department_id = employee.department_id\n" +
            "WHERE YEAR(sign_in.create_date) = #{year} AND MONTH(sign_in.create_date) = #{month}  AND " +
            "DAY(sign_in.create_date)= #{day} \n" +
            "<if test='status != null and status == 1'>\n" +
            " AND employee.name LIKE CONCAT('%', #{data}, '%')\n" +
            "</if>\n" +
            "<if test='status != null and status == 2'>\n" +
            " AND employee.employee_id = #{data}\n" +
            "</if>\n" +

            "</script>")


    int getTotalHistory(@Param("data") String data, @Param("status") Integer status,@Param("year") String year, @Param("month") String month,@Param("day") String day);


    @Insert("insert into sign_in(employee_id, sign_date,type,publish,create_date) values(#{employeeId}, #{signDate}, #{publish},#{type},Now())")
    int addSignIn(Integer employeeId, Timestamp signDate, Integer publish, Integer type);

    @Delete("delete from sign_in where sign_id = #{signId}")
    int deleteSignIn(Integer signId);

}
