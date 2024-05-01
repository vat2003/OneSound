package com.project.shopapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.entity.Role;

/**
 * RoleDAO
 */
public interface RoleDAO extends JpaRepository<Role, Long> {

    Optional<Role> findById(int i);
}