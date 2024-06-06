package com.oaem.Controller;

import com.oaem.Pojo.Login;
import com.oaem.Pojo.SimReturn;
import com.oaem.Service.EmployeeService;
import com.oaem.Service.LoginService;
import com.oaem.Service.SignInService;
import com.oaem.Utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Map;

@RestController
@RequestMapping("/signIn")
public class SignInController {
    @Autowired
    private SignInService signInService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/add")
    @Transactional
    public SimReturn add(@RequestHeader("Authorization") String token, Timestamp date, Integer type) {
        if (token == null || token.isEmpty())
            return new SimReturn(401, "未登录", null);
        String account = Token.parseToken(token);
        if (account == null)
            return new SimReturn(401, "信息过期, 请重新登录", null);
            int l= loginService.getLoginId(account);
           Map<String, Object> map=employeeService.getEmployeeByLoginId(l);
          int employee_id = (int) map.get("employee_id");
          if (signInService.addSignIn(employee_id, date,employee_id,type)>0) {
              return new SimReturn(200, "签到成功", null);
          } else {
              return new SimReturn(400, "签到失败", null);
          }
    }

    @GetMapping("/getSignInHistory/{limit}/{offset}/{data}/{status}/{year}/{month}/{day}")
    @Transactional
    public SimReturn getHolidaysHistory(
            @PathVariable("limit") Integer limit,
            @PathVariable("offset") Integer offset,
            @PathVariable("data") String data,
            @PathVariable("status") Integer status,
            @PathVariable("year") String year,
            @PathVariable("month") String month,
            @PathVariable("day") String day
           ) {

        try {
            return new SimReturn(200, "获取补签申请历史成功", signInService.getAllSignInHistory(limit, offset, data, status, year, month, day));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取补签申请历史失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }


    @GetMapping("/getTotalHIstory/{data}/{status}/{year}/{month}/{day}")
    @Transactional
    public SimReturn getTotal(@PathVariable("data") String data,@PathVariable("status") Integer status, @PathVariable("year") String year,
                              @PathVariable("month") String month,@PathVariable("day") String day) {
        try {
            return new SimReturn(200, "获取补签申请历史总数成功",signInService.getTotalHistory(data, status, year, month,day));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取补签申请历史总数失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }

    @PostMapping("/delete/{id}")
    @Transactional
    public SimReturn delete(@PathVariable("id") Integer id) {
        try {
            if (signInService.deleteSignIn(id)>0) {
                return new SimReturn(200, "撤销签到成功", null);
            } else {
                return new SimReturn(400, "撤销签到失败", null);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "撤销签到失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }
}
