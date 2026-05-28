package com.sistema.hotel.dto.hotel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record HotelRequestDto(
    @NotBlank
    @Size(max = 150)
    String name,

    @Size(max = 18)
    String cnpj,

    @Email
    @Size(max = 150)
    String email,

    @Size(max = 20)
    String phone,

    @NotBlank
    @Size(max = 150)
    String street,

    @NotBlank
    @Size(max = 20)
    String number,

    @Size(max = 100)
    String complement,

    @NotBlank
    @Size(max = 100)
    String neighborhood,

    @NotBlank
    @Size(max = 100)
    String city,

    @NotBlank
    @Size(max = 2)
    String state,

    @NotBlank
    @Size(max = 10)
    String zipCode
) {
}
