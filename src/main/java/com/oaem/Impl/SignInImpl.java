package com.oaem.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oaem.Mapper.SignInMapper;
import com.oaem.Pojo.Login;
import com.oaem.Pojo.SignIn;
import com.oaem.Service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
public class SignInImpl extends ServiceImpl<SignInMapper, SignIn> implements SignInService {
    @Autowired
    private SignInMapper signInMapper;
    @Override
    public List<Map<String, Object>> getAllSignInHistory(int limit, int offset, String data, Integer status, String year, String month, String day) {
        return signInMapper.getAllSignInHistory(limit, offset, data, status, year, month, day);
    }

    @Override
    public int getTotalHistory(String data, Integer status, String year, String month, String day) {
        return signInMapper.getTotalHistory(data, status, year, month, day);
    }

    @Override
    public int addSignIn(Integer employeeId, Timestamp signDate, Integer publish, Integer type) {
        return signInMapper.addSignIn(employeeId, signDate, publish, type);
    }

    @Override
    public int deleteSignIn(Integer signId) {
        return signInMapper.deleteSignIn(signId);
    }


}
