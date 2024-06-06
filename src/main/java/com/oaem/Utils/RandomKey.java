package com.oaem.Utils;

import java.security.SecureRandom;
import java.util.Base64;

public class RandomKey {
    public static String generateRandomKey() {
        // 定义密钥长度
        int keyLength = 32; // 16 字节，128 位

        // 创建一个随机的字节数组作为密钥
        byte[] keyBytes = new byte[keyLength];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(keyBytes);

        // 将字节数组转换为 Base64 编码的字符串
        String base64EncodedKey = Base64.getEncoder().encodeToString(keyBytes);

        return base64EncodedKey;
    }


}
