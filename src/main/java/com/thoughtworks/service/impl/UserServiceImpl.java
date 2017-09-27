package com.thoughtworks.service.impl;

import com.thoughtworks.entity.User;
import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.service.UserService;
import com.thoughtworks.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
        public boolean createUser(User user) {
        if (user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getAge() == 0) {
            return false;
        }
        user.setId(StringUtils.randomUUID());
        userRepository.save(user);
        return true;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean updateUserAge(String username, User user) {
        if (userRepository.findUserByUsername(username).getUsername().equals(user.getUsername())) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getUsersByAge(int age) {
        return userRepository.findUsersByAge(age);
    }
}
