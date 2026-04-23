package com.sistema.hotel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.hotel.dto.client.ClientRequestDto;
import com.sistema.hotel.dto.client.ClientResponseDto;
import com.sistema.hotel.service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<ClientResponseDto> createClient(@Valid @RequestBody ClientRequestDto requestDto) {
        ClientResponseDto responseDto = clientService.addClient(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
