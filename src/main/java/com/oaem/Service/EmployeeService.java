package com.oaem.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oaem.Pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface EmployeeService extends IService<Employee> {

    List<Map<String, Object>> getAllEmployees( int limit,  int offset, String data,  Integer status);

    int getTotal( String data,  Integer status);

    List<Map<String, Object>> getAllEmployeesInformation( int limit,  int offset,  String data, Integer status);

    int insertEmployee( String name, String contact_information, Integer department_id, String address, Timestamp hiredate, Double base_pay,Integer login);

    Map<String, Object> getEmployeeById(Integer employee_id);

    int updateEmployee(String name, String phone, Integer Did, String address,  Double pay, Integer id);

    int deleteEmployee(Integer employee_id);

    Map<String, Object> getEmployeeByLoginId(Integer login_id);
}
