package com.oaem.Utils;

import com.oaem.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class CreateAccount {
    public static String generateUniqueNumber() {
        // 获取当前时间的毫秒数
        long timestamp = new Date().getTime();

        // 将毫秒数转换为10位字符串
        String uniqueNumber = String.valueOf(timestamp).substring(0, 10);

        return uniqueNumber;
    }
    @Autowired
    private LoginService loginService;

    public String register(){
        String account = generateUniqueNumber();
        try {

            String hashValue = HashUtils.generate16BitHash(account);
            String AesValue = AesEncryptorAndDecryptor.encrypt("123456789", hashValue, hashValue);
            loginService.insertLogin(account, AesValue,0);
        }catch (Exception e) {
            e.printStackTrace();
        }


        return account;
    }
}
