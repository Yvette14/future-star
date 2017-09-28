package com.thoughtworks.configuration.security;

import com.thoughtworks.entity.JWTUser;
import com.thoughtworks.service.AuthService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (!authService.hasJWTToken(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        handleHasJWTToken(request, response, filterChain);
    }

    private void handleHasJWTToken(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            JWTUser jwtUser = authService.getAuthorizedJWTUser(request);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | SignatureException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
