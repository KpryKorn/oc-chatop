package com.projet3.projet3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet3.projet3.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
