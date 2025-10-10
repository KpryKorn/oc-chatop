package com.projet3.projet3.controller;

import com.projet3.projet3.dto.RentalRequestDTO;
import com.projet3.projet3.entity.Rental;
import com.projet3.projet3.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    public ResponseEntity<Rental> createRental(@RequestBody RentalRequestDTO rentalRequestDTO) {
        Rental rental = rentalService.createRental(rentalRequestDTO);
        return ResponseEntity.ok(rental);
    }

    @GetMapping
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Long id) {
        Rental rental = rentalService.getRentalById(id);
        if (rental == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rental);
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
