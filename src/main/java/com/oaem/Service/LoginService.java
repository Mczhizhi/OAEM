package com.oaem.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.oaem.Pojo.Login;

import java.util.Map;

public interface LoginService extends IService<Login> {
    int insertLogin(String account, String password, Integer authority);
   int Login(String account, String password);
    int getLoginId(String account);
    Login getLogin(String account);
    Map<String, Object> getLoginByAccount(String account);

}
