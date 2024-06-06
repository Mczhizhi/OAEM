package com.oaem.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oaem.Mapper.ReportAbsenceMapper;
import com.oaem.Mapper.SignInReplenishmentMapper;
import com.oaem.Pojo.ReportAbsence;
import com.oaem.Pojo.SignInReplenishment;
import com.oaem.Service.ReportAbsenceService;
import com.oaem.Service.SignInReplenishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SignInReplenishmentImpl extends ServiceImpl<SignInReplenishmentMapper, SignInReplenishment> implements SignInReplenishmentService {

    @Autowired
    private SignInReplenishmentMapper signInReplenishmentMapper;

    @Override
    public List<Map<String, Object>> getAllSignInReplenishment(int limit, int offset, String data, Integer status) {
        return signInReplenishmentMapper.getAllSignInReplenishment(limit, offset, data, status);
    }

    @Override
    public int updateStatus(Integer status, Integer holidayId) {
        return signInReplenishmentMapper.updateStatus(status, holidayId);
    }

    @Override
    public int getTotal(String data, Integer status) {
        return signInReplenishmentMapper.getTotal(data, status);
    }

    @Override
    public List<Map<String, Object>> getAllRenewalHistory(int limit, int offset, String data, Integer status, String year, String month) {
        return signInReplenishmentMapper.getAllRenewalHistory(limit, offset, data, status, year, month);
    }

    @Override
    public int getTotalHistory(String data, Integer status, String year, String month) {
        return signInReplenishmentMapper.getTotalHistory(data, status, year, month);
    }
}
