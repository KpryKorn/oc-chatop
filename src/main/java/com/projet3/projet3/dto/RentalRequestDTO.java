package com.projet3.projet3.dto;

import lombok.Data;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class RentalRequestDTO {
    private String name;
    private Double surface;
    private Double price;
    private String picture;
    private String description;
    private Long ownerId;

    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDateTime updatedAt;
}
