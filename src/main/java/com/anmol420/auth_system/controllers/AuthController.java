package com.anmol420.auth_system.controllers;

import com.anmol420.auth_system.config.JwtUtils;
import com.anmol420.auth_system.domain.dtos.AuthRequest;
import com.anmol420.auth_system.domain.dtos.AuthResponse;
import com.anmol420.auth_system.domain.dtos.RegisterRequest;
import com.anmol420.auth_system.domain.entities.User;
import com.anmol420.auth_system.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtUtils jwtUtils;

    @PostMapping(path = "/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        authService.register(registerRequest);
        return ResponseEntity.ok(new AuthResponse("User Registered Successfully!"));
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody AuthRequest authRequest,
            HttpServletResponse response
    ) {
        User user = authService.authenticate(authRequest);
        if (null == user) {
            return ResponseEntity.status(401).body(new AuthResponse("Invalid Credentials!"));
        }

        String token = jwtUtils.generateToken(user.getUsername());
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(24*60*60*60);
        response.addCookie(cookie);

        return ResponseEntity.ok(new AuthResponse("Login Successful!"));
    }

    @PostMapping(path = "/logout")
    public ResponseEntity<AuthResponse> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(new AuthResponse("Logout Successful!"));
    }
}
