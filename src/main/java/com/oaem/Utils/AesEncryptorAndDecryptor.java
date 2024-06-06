package com.oaem.Utils;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AesEncryptorAndDecryptor {

    // 加密算法、填充方式和数据块大小
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int BLOCK_SIZE = 16;

    /**
     * 对字符串进行AES加密
     * @param inputString 要加密的字符串
     * @param key 密钥（长度必须为16、24或32个字节）
     * @param iv 初始向量（长度必须为16个字节）
     * @return 加密后的字符串
     * @throws Exception 异常
     */
    public static String encrypt(String inputString, String key, String iv) throws Exception {
        // 创建加密对象
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // 根据密钥和算法创建密钥规则
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");

        // 根据初始向量创建初始化向量规则
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());

        // 初始化加密对象
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // 加密字符串
        byte[] encryptedBytes = cipher.doFinal(inputString.getBytes());

        // 将加密结果进行Base64编码
        String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);

        // 替换 '/' 字符为 '-'
        encryptedString = encryptedString.replace('/', '-');

        return encryptedString;
    }

    public static String decrypt(String encryptedString, String key, String iv) throws Exception {
        // 恢复替换的字符
        encryptedString = encryptedString.replace('-', '/');

        // 创建解密对象
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // 根据密钥和算法创建密钥规则
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");

        // 根据初始向量创建初始化向量规则
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());

        // 初始化解密对象
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // 对Base64编码的加密字符串进行解码
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedString);

        // 解密字符串
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // 将解密结果转换为字符串
        return new String(decryptedBytes);
    }


    // 测试代码
//    public static void main(String[] args) throws Exception {
//        // 要加密的字符串
//        String inputString = "hello world";
//
//        // 密钥和初始向量
//        String key = "1234567890123456";
//        String iv = "1234567890123456";
//
//        // 对字符串进行加密
//        String encryptedResult = encrypt(inputString, key, iv);
//
//        System.out.println("AES加密结果：" + encryptedResult);
//    }
}
