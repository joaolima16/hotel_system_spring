package com.sistema.hotel.dto.bedroom;

import java.math.BigDecimal;

import com.sistema.hotel.entity.BedroomStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record BedroomRequestDto(
    @NotNull
    Long hotelId,

    @NotBlank
    @Size(max = 20)
    String number,

    Integer floor,

    @NotBlank
    @Size(max = 50)
    String type,

    @NotNull
    @Positive
    Integer capacity,

    @NotNull
    @Positive
    BigDecimal dailyRate,

    BedroomStatus status,

    @Size(max = 255)
    String description
) {
}
