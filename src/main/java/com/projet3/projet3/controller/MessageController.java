package com.projet3.projet3.controller;

import com.projet3.projet3.dto.MessageRequestDTO;
import com.projet3.projet3.entity.Message;
import com.projet3.projet3.service.MessageService;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<?> createMessage(@RequestBody MessageRequestDTO messageRequestDTO) {
        try {
            Message message = messageService.createMessage(messageRequestDTO);
            return ResponseEntity.ok(Map.of("message", "Message sent with success"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
