package com.oaem.Controller;

import com.oaem.Pojo.Employee;
import com.oaem.Pojo.Login;
import com.oaem.Pojo.SimReturn;
import com.oaem.Service.EmployeeService;
import com.oaem.Service.LoginService;
import com.oaem.Utils.AesEncryptorAndDecryptor;
import com.oaem.Utils.HashUtils;
import com.oaem.Utils.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/login")
    public SimReturn login(String account, String password) {

        if(account == null || password == null)

            return new SimReturn(400, "账号或密码不能为空", null);
        try {
            String hashValue = HashUtils.generate16BitHash(account);
            String AesValue = AesEncryptorAndDecryptor.encrypt(password, hashValue, hashValue);
            int result = loginService.Login(account, AesValue);
            Map<String, Object> map = new HashMap<>();
            if (result == 1) {
                String token =   Token.generateToken(account);

                map.put("flag", true);
                map.put("token", token);

                Login l= loginService.getLogin(account);
                map.put("authority", l.getAuthority());

                Map<String, Object> e= employeeService.getEmployeeByLoginId(l.getLoginId());
                map.put("name", e.get("name"));
                System.out.println(token);
                return new SimReturn(200, "查询成功", map);
            } else {
                map.put("flag", false);
                return new SimReturn(401, "登录失败，请检查账号或密码", map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new SimReturn(500, "服务器内部错误", null);
        }
    }
}
