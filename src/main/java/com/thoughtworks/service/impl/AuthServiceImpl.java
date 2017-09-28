package com.thoughtworks.service.impl;

import com.thoughtworks.dto.LoginBody;
import com.thoughtworks.entity.JWTUser;
import com.thoughtworks.exception.IllegalArgumentException;
import com.thoughtworks.exception.InvalidCredentialException;
import com.thoughtworks.repository.TokenAuthRepository;
import com.thoughtworks.service.AuthService;
import com.thoughtworks.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    private static final String PREFIX_BLACK_LIST = "SSJ-BLACKLIST-";

    @Value("${security.jwt.token-prefix:Bearer}")
    private String tokenPrefix;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.expiration-in-seconds}")
    private long expirationInSeconds;

    @Autowired
    private TokenAuthRepository authRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public JWTUser login(HttpServletResponse response, LoginBody loginRequestUser) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestUser.getUsername(), loginRequestUser.getPassword()));
            JWTUser principal = (JWTUser) authenticate.getPrincipal();

            Map payload = StringUtils.readJsonStringAsObject(StringUtils.writeObjectAsJsonString(principal), Map.class);

            response.addHeader(header, String.join(" ", tokenPrefix,
                    authRepository.generateToken(payload)));
            return principal;
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("Password is invalid!");
        }

    }

    @Override
    public void logout(HttpServletRequest request) {
        String token = extractToken(request);
        String key = PREFIX_BLACK_LIST + token;

    }

    @Override
    public JWTUser getAuthorizedJWTUser(HttpServletRequest request) {
        String payload = authRepository.extractAuthorizedPayload(extractToken(request));
        return StringUtils.readJsonStringAsObject(payload, JWTUser.class);
    }

    @Override
    public boolean hasJWTToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(header);
        return StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith(tokenPrefix);
    }

    private String extractToken(HttpServletRequest request) {
        if (!hasJWTToken(request)) {
            throw new InvalidCredentialException("");
        }
        return request.getHeader(header).substring(tokenPrefix.length() + 1);
    }
}
