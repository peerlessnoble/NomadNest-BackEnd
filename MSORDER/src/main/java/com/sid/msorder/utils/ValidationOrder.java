package com.sid.msorder.utils;

public class ValidationOrder {

    public static boolean isNull(String field) {
        return field == null || field.trim().isEmpty();
    }

}
