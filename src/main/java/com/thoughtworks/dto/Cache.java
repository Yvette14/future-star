package com.thoughtworks.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cache {

    public static final Map<String, User> users = new HashMap<>();

    static {
        User user1 = new User("future_star", "123456",22);
        User user2 = new User("Yibing", "123456",23);

        users.put("future_star", user1);
        users.put("Yibing", user2);
    }

    public boolean createUser(User user) {
        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            return false;
        }
        users.put(user.getUsername(), user);
        return true;
    }

    public boolean login(User user) {
        if (users.get(user.getUsername()).getPassword().equals(user.getPassword())) {
            return true;
        }
        return false;
    }

    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        for (Map.Entry<String, User> entry : users.entrySet()) {
            userList.add(entry.getValue());
        }
        return userList;
    }

    public boolean updateUserAge(String username, User user) {
        if (users.get(username).getUsername().equals(user.getUsername())) {
            users.put(username, user);
            return true;
        }
        return false;
    }
}
