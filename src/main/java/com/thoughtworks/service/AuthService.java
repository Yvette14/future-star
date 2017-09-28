package com.thoughtworks.service;

import com.thoughtworks.dto.LoginBody;
import com.thoughtworks.entity.JWTUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    void logout(HttpServletRequest authorization);

    JWTUser getAuthorizedJWTUser(HttpServletRequest request);

    boolean hasJWTToken(HttpServletRequest request);

    JWTUser login(HttpServletResponse response, LoginBody loginRequestUser);
}