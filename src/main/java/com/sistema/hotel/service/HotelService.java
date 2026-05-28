package com.sistema.hotel.service;

import java.time.LocalDateTime;

import com.sistema.hotel.dto.hotel.HotelUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.hotel.dto.hotel.HotelRequestDto;
import com.sistema.hotel.dto.hotel.HotelResponseDto;
import com.sistema.hotel.entity.Hotel;
import com.sistema.hotel.infra.exception.ResourceNotFoundException;
import com.sistema.hotel.repository.HotelRepository;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public HotelResponseDto getHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        return toResponseDto(hotel);
    }

    @Transactional
    public HotelResponseDto addHotel(HotelRequestDto requestDto) {
        Hotel hotel = buildHotel(requestDto);
        Hotel savedHotel = hotelRepository.save(hotel);
        return toResponseDto(savedHotel);
    }
    @Transactional
    public void deleteHotel(Long id){
        Hotel hotel = hotelRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        hotelRepository.delete(hotel);

    }
    public HotelResponseDto updateHotel(Long id, HotelUpdateRequestDto hotelUpdateRequestDto){
        Hotel hotel = hotelRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        hotel.setStreet(hotelUpdateRequestDto.street());
        hotel.setNumber(hotelUpdateRequestDto.number());
        hotel.setComplement(hotelUpdateRequestDto.complement());
        hotel.setNeighborhood(hotelUpdateRequestDto.neighborhood());
        hotel.setCity(hotelUpdateRequestDto.city());
        hotel.setState(hotelUpdateRequestDto.state());
        hotel.setZipCode(hotelUpdateRequestDto.zipCode());
        hotel.setUpdatedAt(LocalDateTime.now());
        hotelRepository.save(hotel);
        return toResponseDto(hotel);
    }
    private Hotel buildHotel(HotelRequestDto requestDto) {
        LocalDateTime now = LocalDateTime.now();

        return Hotel.builder()
            .name(requestDto.name())
            .cnpj(requestDto.cnpj())
            .email(requestDto.email())
            .phone(requestDto.phone())
            .street(requestDto.street())
            .number(requestDto.number())
            .complement(requestDto.complement())
            .neighborhood(requestDto.neighborhood())
            .city(requestDto.city())
            .state(requestDto.state())
            .zipCode(requestDto.zipCode())
            .createdAt(now)
            .updatedAt(now)
            .build();
    }

    private HotelResponseDto toResponseDto(Hotel hotel) {
        return new HotelResponseDto(
            hotel.getId(),
            hotel.getName(),
            hotel.getCnpj(),
            hotel.getEmail(),
            hotel.getPhone(),
            hotel.getStreet(),
            hotel.getNumber(),
            hotel.getComplement(),
            hotel.getNeighborhood(),
            hotel.getCity(),
            hotel.getState(),
            hotel.getZipCode(),
            hotel.getCreatedAt(),
            hotel.getUpdatedAt()
        );
    }
}
