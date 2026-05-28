package com.sistema.hotel.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.hotel.dto.bedroom.BedroomRequestDto;
import com.sistema.hotel.dto.bedroom.BedroomResponseDto;
import com.sistema.hotel.dto.bedroom.BedroomUpdateRequestDto;
import com.sistema.hotel.entity.Bedroom;
import com.sistema.hotel.entity.BedroomStatus;
import com.sistema.hotel.entity.Hotel;
import com.sistema.hotel.infra.exception.ResourceNotFoundException;
import com.sistema.hotel.repository.BedroomRepository;
import com.sistema.hotel.repository.HotelRepository;

@Service
public class BedroomService {

    private final BedroomRepository bedroomRepository;
    private final HotelRepository hotelRepository;

    public BedroomService(BedroomRepository bedroomRepository, HotelRepository hotelRepository) {
        this.bedroomRepository = bedroomRepository;
        this.hotelRepository = hotelRepository;
    }

    public BedroomResponseDto getBedroom(Long id) {
        Bedroom bedroom = bedroomRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Bedroom not found"));
        return toResponseDto(bedroom);
    }

    @Transactional
    public BedroomResponseDto addBedroom(BedroomRequestDto requestDto) {
        Hotel hotel = hotelRepository.findById(requestDto.hotelId())
            .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        Bedroom bedroom = buildBedroom(requestDto, hotel);
        Bedroom savedBedroom = bedroomRepository.save(bedroom);
        return toResponseDto(savedBedroom);
    }

    @Transactional
    public BedroomResponseDto updateBedroom(Long id, BedroomUpdateRequestDto requestDto) {
        Bedroom bedroom = bedroomRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Bedroom not found"));

        bedroom.setNumber(requestDto.number());
        bedroom.setFloor(requestDto.floor());
        bedroom.setType(requestDto.type());
        bedroom.setCapacity(requestDto.capacity());
        bedroom.setDailyRate(requestDto.dailyRate());
        bedroom.setStatus(requestDto.status());
        bedroom.setDescription(requestDto.description());
        bedroom.setUpdatedAt(LocalDateTime.now());

        bedroomRepository.save(bedroom);
        return toResponseDto(bedroom);
    }

    @Transactional
    public void deleteBedroom(Long id) {
        Bedroom bedroom = bedroomRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Bedroom not found"));
        bedroomRepository.delete(bedroom);
    }

    private Bedroom buildBedroom(BedroomRequestDto requestDto, Hotel hotel) {
        LocalDateTime now = LocalDateTime.now();

        return Bedroom.builder()
            .hotel(hotel)
            .number(requestDto.number())
            .floor(requestDto.floor())
            .type(requestDto.type())
            .capacity(requestDto.capacity())
            .dailyRate(requestDto.dailyRate())
            .status(requestDto.status() != null ? requestDto.status() : BedroomStatus.AVAILABLE)
            .description(requestDto.description())
            .createdAt(now)
            .updatedAt(now)
            .build();
    }

    private BedroomResponseDto toResponseDto(Bedroom bedroom) {
        return new BedroomResponseDto(
            bedroom.getId(),
            bedroom.getHotel().getId(),
            bedroom.getNumber(),
            bedroom.getFloor(),
            bedroom.getType(),
            bedroom.getCapacity(),
            bedroom.getDailyRate(),
            bedroom.getStatus(),
            bedroom.getDescription(),
            bedroom.getCreatedAt(),
            bedroom.getUpdatedAt()
        );
    }
}
