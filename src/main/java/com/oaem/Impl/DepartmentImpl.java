package com.oaem.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.oaem.Mapper.DepartmentMapper;
import com.oaem.Pojo.Department;
import com.oaem.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentImpl extends ServiceImpl <DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public int addDepartment(String name, Timestamp departmentCreateDate, String description, Integer principal) {
        return departmentMapper.addDepartment(name, departmentCreateDate, description, principal);
    }

    @Override
    public List<Map<String, Object>> getAllDepartments(int limit, int offset,String data,Integer status) {
        return departmentMapper.getAllDepartments(limit, offset, data, status);
    }

    @Override
    public int getTotal(String data, Integer status) {
        return departmentMapper.getTotal(data, status);
    }

    @Override
    public Map<String, Object> getDepartmentById(Integer id) {
        return departmentMapper.getDepartmentById(id);
    }

    @Override
    public int updateDepartment(String name, String description, Integer principal, Integer id) {
        return departmentMapper.updateDepartment(name, description, principal, id);
    }

    @Override
    public int deleteDepartment(Integer id) {
        return departmentMapper.deleteDepartment(id);
    }

    @Override
    public List<Map<String, Object>> QueryDepartment() {
        return departmentMapper.QueryDepartment();
    }


}
