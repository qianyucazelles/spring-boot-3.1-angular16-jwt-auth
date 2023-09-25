package com.qianyu.jwt.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qianyu.jwt.backend.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByUserName(String username);
    Boolean existsByUserName(String username);
}
