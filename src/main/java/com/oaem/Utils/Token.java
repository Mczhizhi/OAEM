package com.oaem.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class Token {

    private static final String   SECRET_KEY = com.oaem.Utils.RandomKey.generateRandomKey(); // 替换为你自己的密钥

    public static String generateToken(String Appid) {
        // 设置 token 过期时间为 1 小时
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);

        // 使用 jjwt 库构建 token
        String token = Jwts.builder()
                .setSubject(Appid) // 设置 token 主题为用户名
                .setExpiration(expiration) // 设置 token 过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 使用 HS256 签名算法和密钥签署 token
                .compact();

        return token;
    }
    public static String parseToken(String token) {
        try {
            // 使用密钥解析 token，如果密钥不正确会抛出异常
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

            // 提取 token 中的信息，这里假设 subject 就是 Appid
            String Appid = claims.getSubject();

            // 返回提取到的 Appid
            return Appid;
        } catch (Exception e) {
            // token 解析失败，返回 null 或者抛出异常，根据实际情况处理
            return null;
        }
    }

}