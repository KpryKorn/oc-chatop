package com.projet3.projet3.repository;

import com.projet3.projet3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}