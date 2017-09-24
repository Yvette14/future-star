package com.thoughtworks.dto;

import java.util.HashMap;
import java.util.Map;

public class Cache {

    public static final Map<String, User> users = new HashMap<>();

    static {
        User user1 = new User("future_star", "123456", 22);
        User user2 = new User("Yibing", "123456", 23);

        users.put("future_star", user1);
        users.put("Yibing", user2);
    }
}
