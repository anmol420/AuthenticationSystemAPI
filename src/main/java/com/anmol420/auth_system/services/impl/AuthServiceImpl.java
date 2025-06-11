package com.anmol420.auth_system.services.impl;

import com.anmol420.auth_system.domain.dtos.AuthRequest;
import com.anmol420.auth_system.domain.dtos.RegisterRequest;
import com.anmol420.auth_system.domain.entities.User;
import com.anmol420.auth_system.repository.UserRepository;
import com.anmol420.auth_system.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        userRepository.save(user);
    }

    @Override
    public User authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        return userRepository.findByUsername(request.getUsername()).orElse(null);
    }

}
