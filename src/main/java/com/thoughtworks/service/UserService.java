package com.thoughtworks.service;

import com.thoughtworks.dto.User;

import java.util.Map;

public class UserService {

    public boolean createUser(Map<String,User> users,User user) {
        if (user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getAge() ==0) {
            return false;
        }
        users.put(user.getUsername(), user);
        return true;
    }
}
