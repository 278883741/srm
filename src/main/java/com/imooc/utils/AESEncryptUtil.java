package com.imooc.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// AES加密
public class AESEncryptUtil {
    private static final String AES_ALG = "AES";
    private static final String charset = "utf-8";
    private static final String AES_CBC_PCK_ALG = "AES/CBC/PKCS5Padding";
    private static final byte[] AES_IV = initIv(AES_CBC_PCK_ALG);
    /**
     * 加密
     */
    public static String encryptContent(String content, String encryptType, String encryptKey, String charset) throws Exception {
        if (AES_ALG.equals(encryptType)) {
            return aesEncrypt(content, encryptKey);
        } else {
            throw new Exception("当前不支持该算法类型：encrypeType=" + encryptType);
        }
    }
    /**
     * 解密
     */
    public static String decryptContent(String content, String encryptType, String encryptKey, String charset) throws Exception {
        if (AES_ALG.equals(encryptType)) {
            return aesDecrypt(content, encryptKey);
        } else {
            throw new Exception("当前不支持该算法类型：encrypeType=" + encryptType);
        }
    }
    /**
     * AES加密
     */
    public static String aesEncrypt(String content, String aesKey) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PCK_ALG);
            IvParameterSpec iv = new IvParameterSpec(AES_IV);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(aesKey.getBytes(), AES_ALG), iv);
            byte[] encryptBytes = cipher.doFinal(content.getBytes(charset));
            return new String(Base64.encodeBase64(encryptBytes));
        } catch (Exception e) {
            throw new Exception("AES加密失败：Aescontent = " + content + "; charset = " + charset, e);
        }
    }
    /**
     * AES解密
     */
    public static String aesDecrypt(String content, String key) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PCK_ALG);
            IvParameterSpec iv = new IvParameterSpec(initIv(AES_CBC_PCK_ALG));
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), AES_ALG), iv);
            byte[] cleanBytes = cipher.doFinal(Base64.decodeBase64(content.getBytes()));
            return new String(cleanBytes, charset);
        } catch (Exception e) {
            throw new Exception("AES解密失败：Aescontent = " + content + "; charset = " + charset, e);
        }
    }
    private static byte[] initIv(String fullAlg) {
        try {
            Cipher cipher = Cipher.getInstance(fullAlg);
            int blockSize = cipher.getBlockSize();
            byte[] iv = new byte[blockSize];
            for (int i = 0; i < blockSize; ++i) {
                iv[i] = 0;
            }
            return iv;
        } catch (Exception e) {
            return null;
        }
    }
}