package com.oaem.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oaem.Mapper.LoginMapper;
import com.oaem.Pojo.Login;
import com.oaem.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginImpl extends ServiceImpl<LoginMapper, Login> implements LoginService {

    @Autowired
        private LoginMapper loginMapper;
    @Override
    public int insertLogin(String account, String password, Integer authority) {
        return loginMapper.insertLogin(account, password, authority);
    }

    @Override
    public int Login(String account, String password) {
        return loginMapper.Login(account, password);
    }

    @Override
    public int getLoginId(String account) {
        return loginMapper.getLoginId(account);
    }

    @Override
    public Login getLogin(String account) {
        return loginMapper.getLogin(account);
    }

    @Override
    public Map<String, Object> getLoginByAccount(String account) {
        return loginMapper.getLoginByAccount(account);
    }
}
