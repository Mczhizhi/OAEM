package com.oaem.Controller;

import com.oaem.Pojo.Employee;
import com.oaem.Pojo.SimReturn;
import com.oaem.Service.EmployeeService;
import com.oaem.Service.LoginService;
import com.oaem.Utils.AesEncryptorAndDecryptor;
import com.oaem.Utils.CreateAccount;
import com.oaem.Utils.HashUtils;
import com.oaem.Utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private LoginService loginService;
    @GetMapping("/getAll/{limit}/{offset}/{data}/{status}")
    @Transactional
    public SimReturn getAllEmployees(@PathVariable("limit") Integer limit, @PathVariable("offset") Integer offset, @PathVariable("data") String data, @PathVariable("status") Integer status) {
        try {

            return new SimReturn(200, "获取所有员工成功", employeeService.getAllEmployees(limit, offset, data, status));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取所有员工失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }
    @GetMapping("/getAllE/{limit}/{offset}/{data}/{status}")
    @Transactional
    public SimReturn getAllEmployeesInformation(@PathVariable("limit") Integer limit, @PathVariable("offset") Integer offset, @PathVariable("data") String data, @PathVariable("status") Integer status) {
        try {

            return new SimReturn(200, "获取所有员工成功", employeeService.getAllEmployeesInformation(limit, offset, data, status));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取所有员工失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }

    @GetMapping("/getTotal/{data}/{status}")
    @Transactional
    public SimReturn getTotal(@PathVariable("data") String data,@PathVariable("status") Integer status) {


        try {
            return new SimReturn(200, "获取员工总数成功", employeeService.getTotal(data, status));
        } catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取员工总数失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }

    @GetMapping("/getEmployeeInfo")
    @Transactional
    public SimReturn getEmployeeInfo (@RequestHeader("Authorization") String token)
    {
        if (token == null || token.isEmpty()) {
            return new SimReturn(401, "未登录", null);
        }
        try {
            String account = Token.parseToken(token);
            Map<String, Object> map = loginService.getLoginByAccount(account);
            if (map != null)
                return new SimReturn(200, "获取员工信息成功", map);
            else return new SimReturn(500, "获取员工信息失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }catch (Exception e){
            System.out.println(e);
            return new SimReturn(500, "获取员工信息失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }

    @GetMapping("getInfo/{id}")
    @Transactional
    public SimReturn getEmployeeInfo(@PathVariable("id") Integer id) {
        try {
            return new SimReturn(200, "获取员工信息成功", employeeService.getEmployeeById(id));

        }catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "获取员工信息失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }

    @PostMapping("update")
    @Transactional
    public SimReturn updateEmployee(String name, String phone, Integer Did, String address,  Double pay, Integer id) {

        try {
            employeeService.updateEmployee(name, phone, Did, address, pay, id);
            return new SimReturn(200, "更新员工信息成功", null);
        }catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "更新员工信息失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }
    @PostMapping("update/Order")
    @Transactional
    public SimReturn updateEmployeeOrder(@RequestHeader("Authorization")String token, String name, String phone, Integer Did, String address,  Double pay) {
        if (token == null || token.isEmpty()) {
            return new SimReturn(401, "未登录", null);
        }


        try {
            String account = Token.parseToken(token);
            Integer id=(int)loginService.getLoginByAccount(account).get("id");
            employeeService.updateEmployee(name, phone, Did, address, pay, id);
            return new SimReturn(200, "更新员工信息成功", null);
        }catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "更新员工信息失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }
    @PostMapping("delete/{id}")
    @Transactional
    public SimReturn deleteEmployee(@PathVariable("id") Integer id) {
        try {
            employeeService.deleteEmployee(id);
            return new SimReturn(200, "删除员工成功", null);
        }catch (Exception e) {
            System.out.println(e);
            return new SimReturn(500, "删除员工失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }
    @PostMapping("/add")
    @Transactional
    public SimReturn addEmployee(String name, String phone, Integer Did, String address, Timestamp date, Double pay) {
        try {
            String account = generateUniqueNumber();
            String hashValue = HashUtils.generate16BitHash(account);
            String AesValue = AesEncryptorAndDecryptor.encrypt("123456789", hashValue, hashValue);

            loginService.insertLogin(account, AesValue, 0);
           Integer loginId = loginService.getLoginId(account);

            employeeService.insertEmployee(name, phone, Did, address, date, pay, loginId);

            return new SimReturn(200, "添加员工成功", account);
        } catch (Exception e) {
            // 记录异常信息到日志
            System.out.println(e);
            return new SimReturn(500, "添加员工失败,服务器遇到了意料不到的情况，导致无法完成请求", null);
        }
    }


    public static String generateUniqueNumber() {
        // 获取当前时间的毫秒数
        long timestamp = new Date().getTime();

        // 将毫秒数转换为10位字符串
        String uniqueNumber = String.valueOf(timestamp).substring(0, 10);

        return uniqueNumber;
    }




}
