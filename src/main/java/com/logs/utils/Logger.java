package com.logs.utils;

import java.time.LocalDateTime;

public class Logger {

    private final static String PREFIX_INFO = " [INFO] ";
    private final static String PREFIX_ERROR = " [ERROR] ";

    public static void info(String message, Object... params) {
        System.out.println(
                String.format(getInstantTime().concat(PREFIX_INFO).concat(message), params)
        );
    }

    public static void error(String message) {
        System.out.println(getInstantTime().concat(PREFIX_ERROR).concat(message));
    }

    private static String getInstantTime() {
        return LocalDateTime.now().toString();
    }
}
