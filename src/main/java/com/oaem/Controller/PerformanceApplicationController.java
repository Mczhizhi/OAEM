package com.oaem.Controller;

import com.oaem.Pojo.SimReturn;
import com.oaem.Service.LoginService;
import com.oaem.Service.PerformanceApplicationService;
import com.oaem.Service.ReportAbsenceService;
import com.oaem.Utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/performance")
public class PerformanceApplicationController {
    @Autowired
    private PerformanceApplicationService performanceApplicationService;
    @Autowired
    private LoginService loginService;
    @GetMapping("/getAll/{limit}/{offset}/{data}/{status}")
    @Transactional
    public SimReturn getAllPerformanceApplication(@PathVariable("limit") Integer limit, @PathVariable("offset") Integer offset, @PathVariable("data") String data, @PathVariable("status") Integer status) {
        try {

            return new SimReturn(200, "获取所有绩效申请成功",performanceApplicationService.getAllPerformanceApplication(limit, offset, data, status));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取所有绩效申请失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }
    @GetMapping("/getTotal/{data}/{status}")
    @Transactional
    public SimReturn getTotal(@PathVariable("data") String data, @PathVariable("status") Integer status) {


        try {
            return new SimReturn(200, "获取绩效申请总数成功", performanceApplicationService.getTotal(data, status));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取绩效申请总数失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }

    @PostMapping("/update")
    @Transactional
    public SimReturn updateAbsenceStatus(Integer id , Integer status) {
        try {
            performanceApplicationService.updateStatus(id, status);
            return new SimReturn(200, "更新绩效状态成功" + (status == 1 ? "通过" : "拒绝"), null);

        }catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "更新绩效状态失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }
    @GetMapping("/getPerformanceHistory/{limit}/{offset}/{data}/{status}/{year}/{month}")
    @Transactional
    public SimReturn getHolidaysHistory(
            @PathVariable("limit") Integer limit,
            @PathVariable("offset") Integer offset,
            @PathVariable("data") String data,
            @PathVariable("status") Integer status,
            @PathVariable("year") String year,
            @PathVariable("month") String month) {
        try {
            return new SimReturn(200, "获取绩效申请历史成功", performanceApplicationService.getAllPerformanceHistory(limit, offset, data, status, year, month));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取绩效申请历史失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }


    @GetMapping("/getTotalHIstory/{data}/{status}/{year}/{month}")
    @Transactional
    public SimReturn getTotal(@PathVariable("data") String data,@PathVariable("status") Integer status, @PathVariable("year") String year,
                              @PathVariable("month") String month) {
        try {
            return new SimReturn(200, "获取绩效申请历史总数成功",performanceApplicationService.getTotalHistory(data, status, year, month));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取绩效申请历史总数失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }

    @PostMapping("/addPerformance")
    @Transactional
    public SimReturn addHoliday(@RequestHeader("Authorization") String token,Double appliedAmount, Timestamp appliedDate, String description ) {

        if(token == null || token.isEmpty())
            return new SimReturn(401, "未登录,请先登录", null);

        try {
            String account = Token.parseToken(token);
            if (account == null || account.isEmpty())
                return new SimReturn(401, "信息过期,请重新登录登录", null);
            Integer employeeId = (int)loginService.getLoginByAccount(account).get("id");

            performanceApplicationService.insertEmployeeId(employeeId, appliedAmount, appliedDate, description);
            return new SimReturn(200, "添加绩效申请成功", null);
        }catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "添加绩效申请失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }
}
