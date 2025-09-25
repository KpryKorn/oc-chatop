package com.projet3.projet3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet3.projet3.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
