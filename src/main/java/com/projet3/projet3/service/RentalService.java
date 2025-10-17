package com.projet3.projet3.service;

import com.projet3.projet3.dto.RentalRequestDTO;
import com.projet3.projet3.dto.RentalResponseDTO;
import com.projet3.projet3.entity.Rental;
import com.projet3.projet3.entity.User;
import com.projet3.projet3.repository.RentalRepository;
import com.projet3.projet3.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    public RentalService(RentalRepository rentalRepository, UserRepository userRepository,
            FileStorageService fileStorageService) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }

    private String buildPictureUrl(String fileName) {
        return "http://localhost:8080/api/uploads/" + fileName;
    }

    public Rental createRental(RentalRequestDTO dto, MultipartFile pictureFile, String ownerEmail) {
        String fileName = fileStorageService.storeFile(pictureFile);

        User owner = userRepository.findByEmail(ownerEmail)
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

        Rental rental = Rental.builder()
                .name(dto.getName())
                .surface(dto.getSurface())
                .price(dto.getPrice())
                .picture(buildPictureUrl(fileName))
                .description(dto.getDescription())
                .owner(owner)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();
        return rentalRepository.save(rental);
    }

    public List<RentalResponseDTO> getAllRentals() {
        return rentalRepository.findAll().stream().map(rental -> {
            RentalResponseDTO dto = new RentalResponseDTO();
            dto.setId(rental.getId());
            dto.setName(rental.getName());
            dto.setSurface(rental.getSurface());
            dto.setPrice(rental.getPrice());
            dto.setPicture(rental.getPicture());
            dto.setDescription(rental.getDescription());
            dto.setOwnerId(rental.getOwner() != null ? rental.getOwner().getId() : null);
            dto.setCreatedAt(rental.getCreated_at());
            dto.setUpdatedAt(rental.getUpdated_at());
            return dto;
        }).collect(Collectors.toList());
    }

    public RentalResponseDTO getRentalResponseById(Long id) {
        Rental rental = rentalRepository.findById(id).orElse(null);
        if (rental == null) {
            return null;
        }
        RentalResponseDTO dto = new RentalResponseDTO();
        dto.setId(rental.getId());
        dto.setName(rental.getName());
        dto.setSurface(rental.getSurface());
        dto.setPrice(rental.getPrice());
        dto.setPicture(rental.getPicture());
        dto.setDescription(rental.getDescription());
        dto.setOwnerId(rental.getOwner() != null ? rental.getOwner().getId() : null);
        dto.setCreatedAt(rental.getCreated_at());
        dto.setUpdatedAt(rental.getUpdated_at());
        return dto;
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
