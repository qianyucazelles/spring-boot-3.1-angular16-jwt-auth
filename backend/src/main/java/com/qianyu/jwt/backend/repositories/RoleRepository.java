package com.qianyu.jwt.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qianyu.jwt.backend.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}