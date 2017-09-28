package com.thoughtworks.api;

import javax.servlet.http.HttpServletResponse;
import com.thoughtworks.dto.LoginBody;
import com.thoughtworks.entity.JWTUser;
import com.thoughtworks.service.AuthService;
import com.thoughtworks.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
