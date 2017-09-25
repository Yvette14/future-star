package com.thoughtworks.util;

import java.util.UUID;

public final class StringUtils {
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }
}
