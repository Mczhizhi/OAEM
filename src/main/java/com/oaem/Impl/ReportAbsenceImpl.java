package com.oaem.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oaem.Mapper.ReportAbsenceMapper;
import com.oaem.Pojo.ReportAbsence;
import com.oaem.Service.ReportAbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportAbsenceImpl extends ServiceImpl<ReportAbsenceMapper, ReportAbsence> implements ReportAbsenceService {

    @Autowired
    private ReportAbsenceMapper reportAbsenceMapper;

    @Override
    public List<Map<String, Object>> getAllReportAbsence(int limit, int offset, String data, Integer status) {
        return reportAbsenceMapper.getAllReportAbsence(limit, offset, data, status);
    }

    @Override
    public int updateStatus(Integer status, Integer holidayId) {
        return reportAbsenceMapper.updateStatus(status, holidayId);
    }

    @Override
    public int getTotal(String data, Integer status) {
        return reportAbsenceMapper.getTotal(data, status);
    }

    @Override
    public List<Map<String, Object>> getAllAbsenceHistory(int limit, int offset, String data, Integer status, String year, String month) {
        return reportAbsenceMapper.getAllAbsenceHistory(limit, offset, data, status, year, month);
    }

    @Override
    public int getTotalHistory(String data, Integer status, String year, String month) {
        return reportAbsenceMapper.getTotalHistory(data, status, year, month);
    }
}
