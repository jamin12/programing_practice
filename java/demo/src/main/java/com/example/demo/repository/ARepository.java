package com.example.demo.repository;

import com.example.demo.domain.A;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ARepository extends JpaRepository<A,Long> {
}
