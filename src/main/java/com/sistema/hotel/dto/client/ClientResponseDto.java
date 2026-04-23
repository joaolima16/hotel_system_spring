package com.sistema.hotel.dto.client;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ClientResponseDto(
    Long id,
    String name,
    String email,
    String cpf,
    String phone,
    LocalDate birthDate,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
