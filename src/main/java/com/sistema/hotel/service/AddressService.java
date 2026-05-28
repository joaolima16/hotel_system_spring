package com.sistema.hotel.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.hotel.dto.address.AddressRequestDto;
import com.sistema.hotel.dto.address.AddressResponseDto;
import com.sistema.hotel.entity.Address;
import com.sistema.hotel.entity.Client;
import com.sistema.hotel.infra.exception.ResourceNotFoundException;
import com.sistema.hotel.repository.AddressRepository;
import com.sistema.hotel.repository.ClientRepository;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;

    public AddressService(AddressRepository addressRepository, ClientRepository clientRepository) {
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
    }

    public AddressResponseDto getAddress(Long id) {
        Address address = addressRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        return toResponseDto(address);
    }

    @Transactional
    public AddressResponseDto addAddress(AddressRequestDto requestDto) {
        Client client = clientRepository.findById(requestDto.clientId())
            .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        Address address = buildAddress(requestDto, client);
        Address savedAddress = addressRepository.save(address);
        return toResponseDto(savedAddress);
    }

    private Address buildAddress(AddressRequestDto requestDto, Client client) {
        LocalDateTime now = LocalDateTime.now();
        return Address.builder()
            .client(client)
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

    private AddressResponseDto toResponseDto(Address address) {
        return new AddressResponseDto(
            address.getId(),
            address.getClient().getId(),
            address.getStreet(),
            address.getNumber(),
            address.getComplement(),
            address.getNeighborhood(),
            address.getCity(),
            address.getState(),
            address.getZipCode(),
            address.getCreatedAt(),
            address.getUpdatedAt()
        );
    }
}
