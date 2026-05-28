package com.sistema.hotel.dto.hotel;

public record HotelUpdateRequestDto (
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String zipCode){

}
