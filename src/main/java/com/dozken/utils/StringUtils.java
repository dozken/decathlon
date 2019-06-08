package com.dozken.utils;

import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isEmpty(final String str) {
        return (str == null || str.trim().length() == 0);
    }

    public static boolean isNotNumeric(String str) {
        return !str.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isNotTime(String str) {
        return !Pattern.matches("^\\d{1,2}(\\.)\\d{1,2}(\\.)\\d{1,2}$", str);
    }

    public static double toSeconds(String run1500mSeconds) {
        String[] data = run1500mSeconds.split("\\.");
        if (data.length == 2) {
            return Double.valueOf(run1500mSeconds);
        } else if (data.length == 3) {
            int minutes = Integer.parseInt(data[0]);
            return minutes * 60 + Double.valueOf(data[1] + "." + data[2]);
        } else {
            throw new RuntimeException("Cannot parse string time to seconds.");
        }
    }
}
