package com.assignment.carmarket.utils;


import com.assignment.carmarket.utils.exceptions.BadRequestHandler;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.regex.Pattern;

public class PasswordUtils {
    public static String generateSalt() {
        return BCrypt.gensalt();
    }

    public static String generatePassword(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    public static void checkFormatPassword(String password) throws BadRequestHandler {
        String formatPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[ -/:-@\\[-`{-~]).{8,16}$";
        boolean matches = Pattern.matches(formatPassword, password);

        if (!matches) {
            throw new BadRequestHandler("รหัสผ่านต้องประกอบด้วยอย่างน้อย 8 ตัวอักษร (สูงสุด 16 ตัวอักษร) " +
                    "ประกอบด้วยตัวพิมพ์ใหญ่ (A-Z), ตัวพิมพ์เล็ก (a-z), ตัวเลข (0-9) และ อักขระอย่างน้อย 1 ตัวเช่น (!@#$%^&*)" +
                    "ตัวอย่างการตั้งค่า รหัสผ่าน เช่น Carmarket!5");
        }
    }

    public static boolean checkMatchPassword(String reqPassword, String accountPassword) {
        return BCrypt.checkpw(reqPassword, accountPassword);
    }
}
