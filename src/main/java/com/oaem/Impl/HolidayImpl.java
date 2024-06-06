package com.oaem.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oaem.Mapper.HolidayMapper;
import com.oaem.Pojo.Holiday;
import com.oaem.Service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class HolidayImpl extends ServiceImpl<HolidayMapper, Holiday> implements HolidayService {
    @Autowired
    private HolidayMapper holidayMapper;

    @Override
    public int getTotal(String data, Integer status) {
        return holidayMapper.getTotal(data, status);
    }

    @Override
    public List<Map<String, Object>> getAllHolidays(int limit, int offset, String data, Integer status) {
        return holidayMapper.getAllHolidays(limit, offset, data, status);
    }

    @Override
    public int updateStatus(Integer status, Integer holidayId) {
        return holidayMapper.updateStatus(status, holidayId);
    }

    @Override
    public List<Map<String, Object>> getAllHolidaysHistory(int limit, int offset, String data, Integer status, String year, String month) {
        return holidayMapper.getAllHolidaysHistory(limit, offset, data, status, year, month);
    }

    @Override
    public int getTotalHistory(String data, Integer status, String year, String month) {
        return holidayMapper.getTotalHistory(data, status, year, month);
    }

    @Override
    public int addEmployeeHoliday(Integer employeeId, Timestamp applicationDate, Timestamp endDate, int type, String cause) {
        return holidayMapper.addEmployeeHoliday(employeeId, applicationDate, endDate, type, cause);
    }


}
