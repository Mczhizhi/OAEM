package com.oaem.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oaem.Pojo.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


public interface DepartmentService extends IService <Department> {
    int addDepartment(String name, Timestamp departmentCreateDate, String description, Integer principal);
    List<Map<String, Object>> getAllDepartments(int limit,int offset,String data, Integer status);
    int getTotal(String data, Integer status);
    Map<String, Object> getDepartmentById(Integer id);
    int updateDepartment(String name, String description, Integer principal, Integer id);
    int deleteDepartment(Integer id);
    List<Map<String,Object>> QueryDepartment();
}
