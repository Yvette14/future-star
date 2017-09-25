package com.thoughtworks.service;

import com.thoughtworks.entity.User;

import java.util.List;

public interface UserService {
    boolean createUser(User user);

    List<User> getUsers();

    boolean updateUserAge(String username, User user);

    List<User> getUsersByAge(int age);
}
