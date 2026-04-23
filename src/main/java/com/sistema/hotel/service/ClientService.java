package com.sistema.hotel.service;

import java.time.LocalDateTime;

import com.sistema.hotel.dto.client.ClientUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sistema.hotel.dto.client.ClientRequestDto;
import com.sistema.hotel.dto.client.ClientResponseDto;
import com.sistema.hotel.entity.Client;
import com.sistema.hotel.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientResponseDto getClient(Long id) {
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client not found"));
        return toResponseDto(client);
    }

    @Transactional
    public ClientResponseDto addClient(ClientRequestDto requestDto) {
        Client client = buildClient(requestDto);
        Client savedClient = saveClient(client);
        return toResponseDto(savedClient);
    }

    private Client buildClient(ClientRequestDto requestDto) {
        LocalDateTime now = LocalDateTime.now();

        return Client.builder()
            .name(requestDto.name())
            .email(requestDto.email())
            .cpf(requestDto.cpf())
            .phone(requestDto.phone())
            .birthDate(requestDto.birthDate())
            .createdAt(now)
            .updatedAt(now)
            .build();
    }

    private Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    private ClientResponseDto toResponseDto(Client client) {
        return new ClientResponseDto(
            client.getId(),
            client.getName(),
            client.getEmail(),
            client.getCpf(),
            client.getPhone(),
            client.getBirthDate(),
            client.getCreatedAt(),
            client.getUpdatedAt()
        );
    }
    @Transactional
    public ClientResponseDto updateClient(Long id, ClientUpdateRequestDto requestDto) {
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Client not found"));
            client.setName(requestDto.name());
            client.setPhone(requestDto.phone());
            client.setBirthDate(requestDto.birthDate());
            client.setUpdatedAt(LocalDateTime.now());
        clientRepository.save(client);
        return toResponseDto(client);
    }
}
