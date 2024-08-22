package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Reserva;

public interface ReservaRepository extends JpaRepository< Reserva, Long >{

}
