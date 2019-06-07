package com.dozken.utils;

public class StringUtils {

    public static boolean isNotEmpty(final String str)
    {
        return (str != null && str.trim().length() > 0);
    }

    public static boolean isEmpty(final String str)
    {
        return (str == null || str.trim().length() == 0);
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isNotNumeric(String str) {
        return !str.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isNotTime(String str) {
        return !str.matches("^(\\d\\d:\\d\\d:\\d\\d)");
    }
}
