package com.oaem.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.oaem.Pojo.PerformanceApplication;
import com.oaem.Pojo.ReportAbsence;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface PerformanceApplicationService extends IService<PerformanceApplication> {
    List<Map<String, Object>> getAllPerformanceApplication( int limit,  int offset,  String data,  Integer status);
    int updateStatus(Integer status, Integer holidayId);
    int getTotal( String data, Integer status);
    List<Map<String, Object>> getAllPerformanceHistory( int limit, int offset,  String data,  Integer status,  String year, String month);
    int getTotalHistory( String data,Integer status, String year,  String month);
    int insertEmployeeId(Integer employeeId, Double appliedAmount, Timestamp appliedDate, String description);

}
