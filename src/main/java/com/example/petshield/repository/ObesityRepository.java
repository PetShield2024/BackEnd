package com.example.petshield.repository;

import com.example.petshield.domain.ObesityData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObesityRepository extends JpaRepository<ObesityData, Long> {
}
