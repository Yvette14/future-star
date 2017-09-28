package com.thoughtworks.service;

import com.thoughtworks.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean createUser(User user);

    List<User> getUsers();

    boolean updateUserAge(String username, User user);

    List<User> getUsersByAge(int age);

}
