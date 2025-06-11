package com.anmol420.auth_system.services;

import com.anmol420.auth_system.domain.dtos.AuthRequest;
import com.anmol420.auth_system.domain.dtos.RegisterRequest;
import com.anmol420.auth_system.domain.entities.User;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    void register(RegisterRequest request);
    User authenticate(AuthRequest request);

}
