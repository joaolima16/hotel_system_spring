package com.sistema.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.hotel.entity.Bedroom;

public interface BedroomRepository extends JpaRepository<Bedroom, Long> {
}
