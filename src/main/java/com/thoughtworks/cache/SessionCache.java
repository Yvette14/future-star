package com.thoughtworks.cache;

import com.thoughtworks.entity.User;
import com.thoughtworks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SessionCache {

    @Autowired
    UserRepository userRepository;

    public static User currentUser;

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public User loadCurrentUser() {
        if (currentUser != null) {
            return currentUser;
        }

        List<User> users = userRepository.findAll();
        currentUser = users.get(0);

        return currentUser;
    }
}
