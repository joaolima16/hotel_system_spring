package com.sistema.hotel.dto.address;

import java.time.LocalDateTime;

public record AddressResponseDto(
    Long id,
    Long clientId,
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
