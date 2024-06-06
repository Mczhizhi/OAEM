package com.oaem.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oaem.Mapper.PayMapper;
import com.oaem.Pojo.Pay;
import com.oaem.Service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PayImpl extends ServiceImpl<PayMapper, Pay> implements PayService {

    @Autowired
        private PayMapper payMapper;
    @Override
    public int getTotal(String data, Integer status) {
        return payMapper.getTotal(data, status);
    }

    @Override
    public List<Map<String, Object>> getAllPay(int limit, int offset, String data, Integer status) {
        return payMapper.getAllPay(limit, offset, data, status);
    }
}
