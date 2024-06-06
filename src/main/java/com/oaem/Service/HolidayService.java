package com.oaem.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oaem.Pojo.Holiday;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface HolidayService extends IService<Holiday> {
    int getTotal(String data, Integer status);
    List<Map<String, Object>> getAllHolidays( int limit,  int offset,  String data,Integer status);
    int updateStatus(Integer status, Integer holidayId);
    List<Map<String, Object>> getAllHolidaysHistory( int limit, int offset,  String data,  Integer status,  String year, String month);
    int getTotalHistory( String data,Integer status, String year,  String month);
    int addEmployeeHoliday(Integer employeeId, Timestamp applicationDate, Timestamp endDate, int type, String cause);

}
