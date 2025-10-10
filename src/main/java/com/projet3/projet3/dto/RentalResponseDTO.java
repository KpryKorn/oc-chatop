package com.projet3.projet3.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RentalResponseDTO {
    private Long id;
    private String name;
    private Double surface;
    private Double price;
    private String picture;
    private String description;
    private Long ownerId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
