package com.sistema.hotel.dto.hotel;

import java.time.LocalDateTime;

public record HotelResponseDto(
    Long id,
    String name,
    String cnpj,
    String email,
    String phone,
    String street,
    String number,
    String complement,
    String neighborhood,
    String city,
    String state,
    String zipCode,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
