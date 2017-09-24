package com.thoughtworks.service;

import com.thoughtworks.dto.Cache;
import com.thoughtworks.dto.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    public boolean createUser(User user) {
        if (user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getAge() == 0) {
            return false;
        }
        Cache.users.put(user.getUsername(), user);
        return true;
    }


    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        for (Map.Entry<String, User> entry : Cache.users.entrySet()) {
            userList.add(entry.getValue());
        }
        return userList;
    }

    public boolean updateUserAge(String username, User user) {
        if (Cache.users.get(username).getUsername().equals(user.getUsername())) {
            Cache.users.put(username, user);
            return true;
        }
        return false;
    }


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
