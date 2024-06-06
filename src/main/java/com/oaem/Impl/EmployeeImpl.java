package com.oaem.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oaem.Mapper.EmployeeMapper;
import com.oaem.Pojo.Employee;
import com.oaem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeImpl extends ServiceImpl <EmployeeMapper, Employee> implements EmployeeService {
    @Autowired
        private EmployeeMapper employeeMapper;
    @Override
    public List<Map<String, Object>> getAllEmployees(int limit, int offset, String data, Integer status) {
        return employeeMapper.getAllEmployees(limit, offset, data, status);
    }

    @Override
    public int getTotal(String data, Integer status) {
        return employeeMapper.getTotal(data, status);
    }

    @Override
    public List<Map<String, Object>> getAllEmployeesInformation(int limit, int offset, String data, Integer status) {
        return employeeMapper.getAllEmployeesInformation(limit, offset, data, status);
    }

    @Override
    public int insertEmployee( String name, String contact_information, Integer department_id, String address, Timestamp hiredate, Double base_pay,Integer login) {
        return employeeMapper.insertEmployee( name, contact_information, department_id, address, hiredate, base_pay,login);
    }

    @Override
    public Map<String, Object> getEmployeeById(Integer employee_id) {
        return employeeMapper.getEmployeeById(employee_id);
    }

    @Override
    public int updateEmployee(String name, String phone, Integer Did, String address,  Double pay, Integer id) {
        return employeeMapper.updateEmployee(name, phone, Did, address, pay, id);
    }

    @Override
    public int deleteEmployee(Integer employee_id) {
        return employeeMapper.deleteEmployee(employee_id);
    }

    @Override
    public Map<String, Object> getEmployeeByLoginId(Integer login_id) {
        return employeeMapper.getEmployeeByLoginId(login_id);
    }
}
