package com.sistema.hotel.dto.bedroom;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.sistema.hotel.entity.BedroomStatus;

public record BedroomResponseDto(
    Long id,
    Long hotelId,
    String number,
    Integer floor,
    String type,
    Integer capacity,
    BigDecimal dailyRate,
    BedroomStatus status,
    String description,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
