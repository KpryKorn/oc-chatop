package com.projet3.projet3.dto;

import lombok.Data;

@Data
public class MessageRequestDTO {
    private Long userId;
    private Long rentalId;
    private String message;
}
