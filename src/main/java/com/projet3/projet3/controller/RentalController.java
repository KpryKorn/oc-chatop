package com.projet3.projet3.controller;

import com.projet3.projet3.dto.RentalRequestDTO;
import com.projet3.projet3.dto.RentalResponseDTO;
import com.projet3.projet3.entity.Rental;
import com.projet3.projet3.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Map<String, String>> createRental(
            @RequestParam("name") String name,
            @RequestParam("surface") Double surface,
            @RequestParam("price") Double price,
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("description") String description,
            @RequestParam("ownerId") Long ownerId) {

        RentalRequestDTO dto = new RentalRequestDTO();
        dto.setName(name);
        dto.setSurface(surface);
        dto.setPrice(price);
        dto.setDescription(description);
        dto.setOwnerId(ownerId);

        rentalService.createRental(dto, picture);
        return ResponseEntity.ok(Map.of("message", "Rental created successfully"));
    }

    @GetMapping
    public ResponseEntity<Map<String, List<RentalResponseDTO>>> getAllRentals() {
        List<RentalResponseDTO> rentals = rentalService.getAllRentals();

        Map<String, List<RentalResponseDTO>> mappedRentals = Map.of("rentals", rentals);
        return ResponseEntity.ok(mappedRentals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponseDTO> getRentalById(@PathVariable Long id) {
        RentalResponseDTO dto = rentalService.getRentalResponseById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rental> updateRental(@PathVariable Long id, @RequestBody RentalRequestDTO rentalRequestDTO) {
        Rental updatedRental = rentalService.updateRental(id, rentalRequestDTO);
        if (updatedRental == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRental);
    }
}
