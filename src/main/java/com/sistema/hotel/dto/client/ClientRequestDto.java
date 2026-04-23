package com.sistema.hotel.dto.client;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClientRequestDto(
    @NotBlank
    @Size(max = 150)
    String name,

    @NotBlank
    @Email
    @Size(max = 150)
    String email,

    @NotBlank
    @Size(max = 14)
    String cpf,

    @Size(max = 20)
    String phone,

    @NotNull
    LocalDate birthDate
) {
}
