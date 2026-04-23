package com.sistema.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.hotel.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
