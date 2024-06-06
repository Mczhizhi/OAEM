package com.oaem.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.oaem.Pojo.Pay;
import com.oaem.Pojo.SignIn;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PayService extends IService<Pay> {
    int getTotal(@Param("data") String data, @Param("status") Integer status);
    List<Map<String, Object>> getAllPay(@Param("limit") int limit, @Param("offset") int offset, @Param("data") String data, @Param("status") Integer status);

}
