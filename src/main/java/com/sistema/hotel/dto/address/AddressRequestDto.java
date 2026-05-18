package com.sistema.hotel.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AddressRequestDto(
    @NotNull
    Long clientId,

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
