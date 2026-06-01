package com.sistema.hotel.dto.auth;

public record TokenResponseDto(
        String token,
        String type
) {
    public static TokenResponseDto bearer(String token) {
        return new TokenResponseDto(token, "Bearer");
    }
}

