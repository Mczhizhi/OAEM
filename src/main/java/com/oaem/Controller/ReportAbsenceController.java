package com.oaem.Controller;

import com.oaem.Pojo.SimReturn;
import com.oaem.Service.HolidayService;
import com.oaem.Service.ReportAbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/absence")
public class ReportAbsenceController {
    @Autowired
    private ReportAbsenceService absenceService;
    @GetMapping("/getAll/{limit}/{offset}/{data}/{status}")
    @Transactional
    public SimReturn getAllAbsence(@PathVariable("limit") Integer limit, @PathVariable("offset") Integer offset, @PathVariable("data") String data, @PathVariable("status") Integer status) {
        try {

            return new SimReturn(200, "获取所有销假申请成功",absenceService.getAllReportAbsence(limit, offset, data, status));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取所有销假申请失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }
    @GetMapping("/getTotal/{data}/{status}")
    @Transactional
    public SimReturn getTotal(@PathVariable("data") String data, @PathVariable("status") Integer status) {


        try {
            return new SimReturn(200, "获取销假申请总数成功", absenceService.getTotal(data, status));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取销假申请总数失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }

    @PostMapping("/update")
    @Transactional
    public SimReturn updateAbsenceStatus(Integer id , Integer status) {
        try {
            absenceService.updateStatus(id, status);
            return new SimReturn(200, "更新请假销假状态成功" + (status == 1 ? "通过" : "拒绝"), null);

        }catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "更新请假销假状态失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }

    @GetMapping("/getAbsenceHistory/{limit}/{offset}/{data}/{status}/{year}/{month}")
    @Transactional
    public SimReturn getHolidaysHistory(
            @PathVariable("limit") Integer limit,
            @PathVariable("offset") Integer offset,
            @PathVariable("data") String data,
            @PathVariable("status") Integer status,
            @PathVariable("year") String year,
            @PathVariable("month") String month) {
        try {
            return new SimReturn(200, "获取销假申请历史成功", absenceService.getAllAbsenceHistory(limit, offset, data, status, year, month));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取销假申请历史失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }


    @GetMapping("/getTotalHIstory/{data}/{status}/{year}/{month}")
    @Transactional
    public SimReturn getTotal(@PathVariable("data") String data,@PathVariable("status") Integer status, @PathVariable("year") String year,
                              @PathVariable("month") String month) {
        try {
            return new SimReturn(200, "获取销假申请历史总数成功",absenceService.getTotalHistory(data, status, year, month));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取销假申请历史总数失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }
}
