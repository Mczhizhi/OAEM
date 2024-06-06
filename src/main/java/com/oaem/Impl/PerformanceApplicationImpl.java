package com.oaem.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oaem.Mapper.PerformanceApplicationMapper;
import com.oaem.Mapper.ReportAbsenceMapper;
import com.oaem.Pojo.PerformanceApplication;
import com.oaem.Pojo.ReportAbsence;
import com.oaem.Service.PerformanceApplicationService;
import com.oaem.Service.ReportAbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class PerformanceApplicationImpl extends ServiceImpl<PerformanceApplicationMapper, PerformanceApplication> implements PerformanceApplicationService {

    @Autowired
    private PerformanceApplicationMapper performanceApplicationMapper;

    @Override
    public List<Map<String, Object>> getAllPerformanceApplication(int limit, int offset, String data, Integer status) {
        return performanceApplicationMapper.getAllPerformanceApplication(limit, offset, data, status);
    }

    @Override
    public int updateStatus(Integer status, Integer holidayId) {
        return performanceApplicationMapper.updateStatus(status, holidayId);
    }

    @Override
    public int getTotal(String data, Integer status) {
        return performanceApplicationMapper.getTotal(data, status);
    }

    @Override
    public List<Map<String, Object>> getAllPerformanceHistory(int limit, int offset, String data, Integer status, String year, String month) {
        return performanceApplicationMapper.getAllPerformanceHistory(limit, offset, data, status, year, month);
    }

    @Override
    public int getTotalHistory(String data, Integer status, String year, String month) {
        return performanceApplicationMapper.getTotalHistory(data, status, year, month);
    }

    @Override
    public int insertEmployeeId(Integer employeeId, Double appliedAmount, Timestamp appliedDate, String description) {
        return performanceApplicationMapper.insertEmployeeId(employeeId, appliedAmount, appliedDate, description);
    }
}
