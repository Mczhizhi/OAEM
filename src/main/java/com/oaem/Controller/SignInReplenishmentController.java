package com.oaem.Controller;

import com.oaem.Pojo.SimReturn;
import com.oaem.Service.ReportAbsenceService;
import com.oaem.Service.SignInReplenishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/renewal")
public class SignInReplenishmentController {
    @Autowired
    private SignInReplenishmentService signInReplenishmentService;
    @GetMapping("/getAll/{limit}/{offset}/{data}/{status}")
    @Transactional
    public SimReturn getAllSignInReplenishment(@PathVariable("limit") Integer limit, @PathVariable("offset") Integer offset, @PathVariable("data") String data, @PathVariable("status") Integer status) {
        try {

            return new SimReturn(200, "获取所有补签申请成功",signInReplenishmentService.getAllSignInReplenishment(limit, offset, data, status));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取所有补签申请失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }
    @GetMapping("/getTotal/{data}/{status}")
    @Transactional
    public SimReturn getTotal(@PathVariable("data") String data, @PathVariable("status") Integer status) {


        try {
            return new SimReturn(200, "获取补签总数成功", signInReplenishmentService.getTotal(data, status));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取补签总数失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }

    @PostMapping("/update")
    @Transactional
    public SimReturn updateAbsenceStatus(Integer id , Integer status) {
        try {
            signInReplenishmentService.updateStatus(id, status);
            return new SimReturn(200, "更新请补签状态成功" + (status == 1 ? "通过" : "拒绝"), null);

        }catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "更新补签状态失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }
    @GetMapping("/getRenewalHistory/{limit}/{offset}/{data}/{status}/{year}/{month}")
    @Transactional
    public SimReturn getHolidaysHistory(
            @PathVariable("limit") Integer limit,
            @PathVariable("offset") Integer offset,
            @PathVariable("data") String data,
            @PathVariable("status") Integer status,
            @PathVariable("year") String year,
            @PathVariable("month") String month) {
        try {
            return new SimReturn(200, "获取补签申请历史成功", signInReplenishmentService.getAllRenewalHistory(limit, offset, data, status, year, month));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取补签申请历史失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }


    @GetMapping("/getTotalHIstory/{data}/{status}/{year}/{month}")
    @Transactional
    public SimReturn getTotal(@PathVariable("data") String data,@PathVariable("status") Integer status, @PathVariable("year") String year,
                              @PathVariable("month") String month) {
        try {
            return new SimReturn(200, "获取补签申请历史总数成功",signInReplenishmentService.getTotalHistory(data, status, year, month));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取补签申请历史总数失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }
}
