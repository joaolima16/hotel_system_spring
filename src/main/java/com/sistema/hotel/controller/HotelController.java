package com.sistema.hotel.controller;

import com.sistema.hotel.dto.hotel.HotelUpdateRequestDto;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sistema.hotel.dto.hotel.HotelRequestDto;
import com.sistema.hotel.dto.hotel.HotelResponseDto;
import com.sistema.hotel.service.HotelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<HotelResponseDto> createHotel(@Valid @RequestBody HotelRequestDto requestDto) {
        HotelResponseDto responseDto = hotelService.addHotel(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelResponseDto> getHotel(@PathVariable Long id) {
        HotelResponseDto responseDto = hotelService.getHotel(id);
        return ResponseEntity.ok(responseDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHotel(@PathVariable Long id){
        hotelService.deleteHotel(id);
        return ResponseEntity.ok("Hotel deleted");
    }
    @PutMapping("/{id}")
    public ResponseEntity<HotelResponseDto> updateHotel(@Valid @PathVariable Long id, @RequestBody HotelUpdateRequestDto hotelUpdateRequestDto){
        HotelResponseDto hotelResponseDto = hotelService.updateHotel(id, hotelUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(hotelResponseDto);
    }
}
