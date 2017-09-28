package com.thoughtworks.service.impl;

import com.thoughtworks.entity.JWTUser;
import com.thoughtworks.entity.Privilege;
import com.thoughtworks.entity.Role;
import com.thoughtworks.entity.User;
import com.thoughtworks.exception.InvalidCredentialException;
import com.thoughtworks.repository.UserRepository;
import com.thoughtworks.service.UserService;
import com.thoughtworks.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public boolean createUser(User user) {
        if (user.getUsername().isEmpty() || user.getPassword().isEmpty() || user.getAge() == 0) {
            return false;
        }
        user.setId(StringUtils.randomUUID());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
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

    @Override
    public JWTUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new InvalidCredentialException("user is not exist!");
        }

        Role role = user.getRole();

        JWTUser jwtUser = JWTUser.builder()
                .username(username)
                .password(user.getPassword())
                .role(role.getSymbol().name())
                .privileges(role.getPrivileges().stream().map(Privilege::getSymbol).collect(Collectors.toList()))
                .build();

        return jwtUser;
    }
}
