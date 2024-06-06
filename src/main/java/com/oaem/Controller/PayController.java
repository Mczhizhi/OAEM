package com.oaem.Controller;

import com.oaem.Pojo.SimReturn;
import com.oaem.Service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired

    private PayService payService;
    @GetMapping("/getAll/{limit}/{offset}/{data}/{status}")
    @Transactional
    public SimReturn getAllPay(@PathVariable("limit") Integer limit, @PathVariable("offset") Integer offset, @PathVariable("data") String data, @PathVariable("status") Integer status) {
        try {

            return new SimReturn(200, "获取所有绩效申请成功",payService.getAllPay(limit, offset, data, status));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取所有绩效申请失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }
    @GetMapping("/getTotal/{data}/{status}")
    @Transactional
    public SimReturn getTotal(@PathVariable("data") String data, @PathVariable("status") Integer status) {


        try {
            return new SimReturn(200, "获取绩效申请总数成功", payService.getTotal(data, status));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取绩效申请总数失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }
}
