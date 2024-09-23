package com.assignment.carmarket.utils;


import com.assignment.carmarket.utils.exceptions.InternalServerErrorHandler;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class UUIDUtils {
    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();

        return uuid.toString();
    }

    public static Date generateExpireTime() throws InternalServerErrorHandler {
        try {
            Calendar date = Calendar.getInstance();
            long timeInSecs = date.getTimeInMillis();
            return new Date(timeInSecs + (6 * 60 * 60 * 1000));
        } catch (Exception ex) {
            throw new InternalServerErrorHandler(ex.getMessage());
        }
    }
}
