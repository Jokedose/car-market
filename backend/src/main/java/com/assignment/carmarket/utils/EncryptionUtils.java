package com.assignment.carmarket.utils;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptionUtils {
    private static final String AES_ALGORITHM = "AES";
    private static final String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";
    @Value("${AES_KEY}")
    private static String aesKey;

    public static byte[] encrypt(byte[] file) throws Exception {
        byte[] secretKeyBytes = Base64.getDecoder().decode(aesKey);
        Key key = new SecretKeySpec(secretKeyBytes, AES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(file);
    }

    public static String decrypt(String encryptedData) throws Exception {
        byte[] secretKeyBytes = Base64.getDecoder().decode(aesKey);
        byte[] data = Base64.getDecoder().decode(encryptedData);
        Key secretKey = new SecretKeySpec(secretKeyBytes, AES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data));
    }

    public static String decryptByte(byte[] encryptedData) throws Exception {
        byte[] secretKeyBytes = Base64.getDecoder().decode(aesKey);
        Key secretKey = new SecretKeySpec(secretKeyBytes, AES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(encryptedData));
    }

    public static String generateKeyString() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();
        byte[] data = secretKey.getEncoded();
        return Base64.getEncoder().encodeToString(data);
    }

}
