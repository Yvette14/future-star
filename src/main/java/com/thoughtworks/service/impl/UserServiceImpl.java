package com.thoughtworks.service.impl;

import com.thoughtworks.dto.Cache;
import com.thoughtworks.dto.User;
import com.thoughtworks.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean createUser(User user) {
        if (user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getAge() == 0) {
            return false;
        }
        Cache.users.put(user.getUsername(), user);
        return true;
    }

    @Override
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        for (Map.Entry<String, User> entry : Cache.users.entrySet()) {
            userList.add(entry.getValue());
        }
        return userList;
    }

    @Override
    public boolean updateUserAge(String username, User user) {
        if (Cache.users.get(username).getUsername().equals(user.getUsername())) {
            Cache.users.put(username, user);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getUsersByAge(int age) {
        List<User> userList = new ArrayList<>();
        for (Map.Entry<String, User> entry : Cache.users.entrySet()) {
            if (entry.getValue().getAge() == age) {
                userList.add(entry.getValue());
            }
        }
        return userList;
    }
}
