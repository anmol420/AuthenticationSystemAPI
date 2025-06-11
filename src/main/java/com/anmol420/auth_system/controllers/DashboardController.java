package com.anmol420.auth_system.controllers;

import com.anmol420.auth_system.config.JwtUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class DashboardController {

    private final JwtUtils jwtUtils;

    @GetMapping("/dashboard")
    public ResponseEntity<?> dashboard(HttpServletRequest request) {
        String token = null;
        for (Cookie cookie: request.getCookies()) {
            System.out.println(cookie);
            if ("jwt".equals(cookie.getName())) {
                token = cookie.getValue();
            }
        }

        if (null == token) {
            return ResponseEntity.status(401).body("Unauthenticated!");
        }

        String username = jwtUtils.extractUsername(token);
        if (null == username) {
            return ResponseEntity.status(401).body("Invalid or Expired Token");
        }

        return ResponseEntity.ok("Hello " + username);
    }

}
