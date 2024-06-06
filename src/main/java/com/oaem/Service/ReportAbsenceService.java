package com.oaem.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.oaem.Pojo.ReportAbsence;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReportAbsenceService extends IService<ReportAbsence> {
    List<Map<String, Object>> getAllReportAbsence( int limit,  int offset,  String data,  Integer status);
    int updateStatus(Integer status, Integer holidayId);
    int getTotal( String data, Integer status);
    List<Map<String, Object>> getAllAbsenceHistory( int limit, int offset,  String data,  Integer status,  String year, String month);
    int getTotalHistory( String data,Integer status, String year,  String month);
}
