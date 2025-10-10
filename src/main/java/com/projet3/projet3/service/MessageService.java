package com.projet3.projet3.service;

import com.projet3.projet3.dto.MessageRequestDTO;
import com.projet3.projet3.entity.Message;
import com.projet3.projet3.entity.Rental;
import com.projet3.projet3.entity.User;
import com.projet3.projet3.repository.MessageRepository;
import com.projet3.projet3.repository.RentalRepository;
import com.projet3.projet3.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final RentalRepository rentalRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository,
            RentalRepository rentalRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    public Message createMessage(MessageRequestDTO dto) {
        if (dto.getUserId() == null || dto.getRentalId() == null || dto.getMessage() == null
                || dto.getMessage().isBlank()) {
            throw new IllegalArgumentException("userId, rentalId et message sont obligatoires");
        }
        User user = userRepository.findById(dto.getUserId()).orElse(null);
        Rental rental = rentalRepository.findById(dto.getRentalId()).orElse(null);
        if (user == null || rental == null) {
            throw new IllegalArgumentException("userId ou rentalId invalide");
        }
        Message message = Message.builder()
                .user(user)
                .rental(rental)
                .message(dto.getMessage())
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();
        return messageRepository.save(message);
    }
}
