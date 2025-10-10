package com.projet3.projet3.service;

import com.projet3.projet3.dto.RentalRequestDTO;
import com.projet3.projet3.entity.Rental;
import com.projet3.projet3.entity.User;
import com.projet3.projet3.repository.RentalRepository;
import com.projet3.projet3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    public RentalService(RentalRepository rentalRepository, UserRepository userRepository) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
    }

    public Rental createRental(RentalRequestDTO dto) {
        User owner = userRepository.findById(dto.getOwnerId()).orElse(null);
        Rental rental = Rental.builder()
                .name(dto.getName())
                .surface(dto.getSurface())
                .price(dto.getPrice())
                .picture(dto.getPicture())
                .description(dto.getDescription())
                .owner(owner)
                .created_at(dto.getCreatedAt())
                .updated_at(dto.getUpdatedAt())
                .build();
        return rentalRepository.save(rental);
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id).orElse(null);
    }

    public Rental updateRental(Long id, RentalRequestDTO dto) {
        Rental rental = rentalRepository.findById(id).orElse(null);
        if (rental == null) {
            return null;
        }
        User owner = userRepository.findById(dto.getOwnerId()).orElse(null);
        rental.setName(dto.getName());
        rental.setSurface(dto.getSurface());
        rental.setPrice(dto.getPrice());
        rental.setPicture(dto.getPicture());
        rental.setDescription(dto.getDescription());
        rental.setOwner(owner);
        rental.setCreated_at(dto.getCreatedAt());
        rental.setUpdated_at(dto.getUpdatedAt());
        return rentalRepository.save(rental);
    }
}
