package com.thoughtworks.service.impl;

import com.thoughtworks.cache.SessionCache;
import com.thoughtworks.entity.User;
import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionCache sessionCache;

    @Override
    public boolean isValid(String username, String password) {

        User user = userRepository.findUserByUsernameAndPassword(username, password);
        if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
            sessionCache.setCurrentUser(user);
            return true;
        } else {
            return false;
        }
    }
}
