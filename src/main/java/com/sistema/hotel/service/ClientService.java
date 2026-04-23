package com.sistema.hotel.service;

import java.time.LocalDateTime;

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
}
