package com.assignment.carmarket.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public static final  String STATUS_SUCCESS = "00";
    public static final String STATUS_ERROR = "99";

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj, String responseCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("responseCode",responseCode);
        map.put("data", responseObj);

        return new ResponseEntity<>(map,status);
    }

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status,String responseCode) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("responseCode",responseCode);

        return new ResponseEntity<>(map,status);
    }
}
