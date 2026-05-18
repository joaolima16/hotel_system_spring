package com.sistema.hotel.controller;

import com.sistema.hotel.dto.address.AddressRequestDto;
import com.sistema.hotel.dto.address.AddressResponseDto;
import com.sistema.hotel.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressResponseDto> createAddress(@Valid @RequestBody AddressRequestDto requestDto) {
        AddressResponseDto responseDto = addressService.addAddress(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDto> getAddress(@PathVariable Long id) {
        AddressResponseDto responseDto = addressService.getAddress(id);
        return ResponseEntity.ok(responseDto);
    }
}
