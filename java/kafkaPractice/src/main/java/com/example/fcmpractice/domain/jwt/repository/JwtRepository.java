package com.example.fcmpractice.domain.jwt.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fcmpractice.domain.jwt.domain.Jwt;

public interface JwtRepository extends JpaRepository<Jwt, UUID> {
}
