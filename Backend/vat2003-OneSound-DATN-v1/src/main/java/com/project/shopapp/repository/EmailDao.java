package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.entity.Email;

public interface EmailDao extends JpaRepository<Email, Long> {
    List<Email> findByEmail(String email);

    Email findUserByEmail(String email);
}
