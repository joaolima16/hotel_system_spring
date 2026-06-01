package com.sistema.hotel.infra.exception.security;

import com.sistema.hotel.entity.User;

public interface TokenService {
    String generateToken(User user);

    String validateAndGetSubject(String token);
}

