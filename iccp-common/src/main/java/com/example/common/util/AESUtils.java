package com.example.common.util;

/**
 * @Description: AES对称加密
 * @Author: yaos
 * @Date: 2019-12-12 21:39
 * @Version：V1.0
 **/

import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AESUtils {
    public static boolean initialized = false;

    public AESUtils() {
    }

    public static String encryptByCS7(String stringToEncode, String key, String iv) {
        initialize();

        try {
            Key keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(1, keySpec, ivSpec);
            byte[] b = cipher.doFinal(stringToEncode.getBytes(StandardCharsets.UTF_8));
            return Base64.encodeBase64String(b);
        } catch (Exception var7) {
            throw new RuntimeException(var7);
        }
    }

    public static String decryptByCS7(String text, String provideKey, String iv) {
        initialize();

        try {
            byte[] byteArray = Base64.decodeBase64(text);
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
            Key key = new SecretKeySpec(provideKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            cipher.init(2, key, ivSpec);
            byte[] ret = cipher.doFinal(byteArray);
            return new String(ret, StandardCharsets.UTF_8);
        } catch (Exception var8) {
            throw new RuntimeException(var8);
        }
    }

    public static void initialize() {
        if (!initialized) {
            Security.addProvider(new BouncyCastleProvider());
            initialized = true;
        }
    }

    public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }

    public static void main(String[] args) {
        String text = "abcdefg";
        String key = "created21d4cekey";
        String iv = "2019000020200000";
        System.out.println(encryptByCS7(text, key, iv));
        System.out.println(decryptByCS7(encryptByCS7(text, key, iv), key, iv));
    }
}