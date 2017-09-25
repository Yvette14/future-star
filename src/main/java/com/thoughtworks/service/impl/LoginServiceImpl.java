package com.thoughtworks.service.impl;

import com.thoughtworks.entity.User;
import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String username, String password) {

        User user = userRepository.findUserByUsernameAndPassword(username, password);
        return user != null && user.getUsername().equals(username) && user.getPassword().equals(password);
    }
}
