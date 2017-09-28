package com.thoughtworks.api;

import com.thoughtworks.dto.LoginBody;
import com.thoughtworks.entity.JWTUser;
import com.thoughtworks.service.AuthService;
import com.thoughtworks.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    AuthService authService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JWTUser login(@RequestBody LoginBody loginBody, HttpServletResponse response) {
        return authService.login(response, loginBody);

    }
}
