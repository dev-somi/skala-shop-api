package com.sk.skala.shopapi.tools;

public class StringUtil {

    public static boolean isAnyEmpty(String... values) {
        for (String value : values) {
            if (value == null || value.isBlank()) {
                return true;
            }
        }
        return false;
    }
    
}
