package com.sistema.hotel.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.hotel.dto.bedroom.BedroomRequestDto;
import com.sistema.hotel.dto.bedroom.BedroomResponseDto;
import com.sistema.hotel.dto.bedroom.BedroomUpdateRequestDto;
import com.sistema.hotel.service.BedroomService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bedroom")
public class BedroomController {

    private final BedroomService bedroomService;

    public BedroomController(BedroomService bedroomService) {
        this.bedroomService = bedroomService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BedroomResponseDto> getBedroom(@PathVariable Long id) {
        BedroomResponseDto responseDto = bedroomService.getBedroom(id);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity<BedroomResponseDto> createBedroom(@Valid @RequestBody BedroomRequestDto requestDto) {
        BedroomResponseDto responseDto = bedroomService.addBedroom(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BedroomResponseDto> updateBedroom(
        @PathVariable Long id,
        @Valid @RequestBody BedroomUpdateRequestDto requestDto
    ) {
        BedroomResponseDto responseDto = bedroomService.updateBedroom(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBedroom(@PathVariable Long id) {
        bedroomService.deleteBedroom(id);
        return ResponseEntity.ok("Bedroom deleted");
    }
}
