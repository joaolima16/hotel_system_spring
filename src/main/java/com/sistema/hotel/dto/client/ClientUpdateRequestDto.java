package com.sistema.hotel.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ClientUpdateRequestDto(
        @NotBlank @Size(max = 150) String name,
        @NotBlank
        String phone,
        @NotNull
        LocalDate birthDate) {
}
