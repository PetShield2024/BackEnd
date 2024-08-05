package com.example.petshield.repository;

import com.example.petshield.domain.Graph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraphRepository extends JpaRepository<Graph, Long> {
}
