package com.thoughtworks.service;

import com.thoughtworks.dto.Cache;
import com.thoughtworks.dto.User;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean login(User user) {
        return Cache.users.get(user.getUsername()).getPassword().equals(user.getPassword());
    }
}
