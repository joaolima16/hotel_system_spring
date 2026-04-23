package com.sistema.hotel.controller;

import com.sistema.hotel.dto.client.ClientUpdateRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> getClient(@PathVariable Long id){
        ClientResponseDto responseDto = clientService.getClient(id);
        return ResponseEntity.ok(responseDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDto> updateClient(@PathVariable Long id, @Valid @RequestBody ClientUpdateRequestDto requestDto){
        ClientResponseDto responseDto = clientService.updateClient(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

}
