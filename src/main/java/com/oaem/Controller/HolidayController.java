package com.oaem.Controller;

import com.oaem.Pojo.SimReturn;
import com.oaem.Service.HolidayService;
import com.oaem.Service.LoginService;
import com.oaem.Utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/holiday")
public class HolidayController {


    @Autowired
    private HolidayService holidayService;

    @Autowired
    private LoginService loginService;

    @GetMapping("/getAll/{limit}/{offset}/{data}/{status}")
    @Transactional
    public SimReturn getAllHolidayInformation(@PathVariable("limit") Integer limit, @PathVariable("offset") Integer offset, @PathVariable("data") String data, @PathVariable("status") Integer status) {
        try {

            return new SimReturn(200, "获取所有请假申请成功",holidayService.getAllHolidays(limit, offset, data, status));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取所有请假申请失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }
    @GetMapping("/getTotal/{data}/{status}")
    @Transactional
    public SimReturn getTotal(@PathVariable("data") String data,@PathVariable("status") Integer status) {


        try {
            return new SimReturn(200, "获取请假申请总数成功",holidayService.getTotal(data, status));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取请假申请总数失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }

    @PostMapping("/update")
    @Transactional
    public SimReturn updateHolidayStatus(Integer id , Integer status) {
        try {
            holidayService.updateStatus( status,id);
            return new SimReturn(200, "更新请假申请状态成功" + (status == 1 ? "通过" : "拒绝"), null);

        }catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "更新请假申请状态失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }


    @GetMapping("/getHolidaysHistory/{limit}/{offset}/{data}/{status}/{year}/{month}")
    @Transactional
    public SimReturn getHolidaysHistory(
            @PathVariable("limit") Integer limit,
            @PathVariable("offset") Integer offset,
            @PathVariable("data") String data,
            @PathVariable("status") Integer status,
            @PathVariable("year") String year,
            @PathVariable("month") String month) {
        try {
            return new SimReturn(200, "获取请假申请历史成功", holidayService.getAllHolidaysHistory(limit, offset, data, status, year, month));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取请假申请历史失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }


    @GetMapping("/getTotalHIstory/{data}/{status}/{year}/{month}")
    @Transactional
    public SimReturn getTotal(@PathVariable("data") String data,@PathVariable("status") Integer status, @PathVariable("year") String year,
                              @PathVariable("month") String month) {
        try {
            return new SimReturn(200, "获取请假申请历史总数成功",holidayService.getTotalHistory(data, status, year, month));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取请假申请历史总数失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }

    @PostMapping("/addHoliday")
    @Transactional
    public SimReturn addHoliday(@RequestHeader("Authorization") String token,Timestamp applicationDate, Timestamp endDate, int type, String cause) {

        if(token == null || token.isEmpty())
            return new SimReturn(401, "未登录,请先登录", null);

        try {
            String account = Token.parseToken(token);
            if (account == null || account.isEmpty())
                return new SimReturn(401, "信息过期,请重新登录登录", null);
            Integer employeeId = (int)loginService.getLoginByAccount(account).get("id");

            holidayService.addEmployeeHoliday(employeeId, applicationDate, endDate, type, cause);
            return new SimReturn(200, "添加请假申请成功", null);
        }catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "添加请假申请失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }


}
