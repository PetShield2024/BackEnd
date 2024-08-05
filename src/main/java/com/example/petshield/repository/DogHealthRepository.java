package com.example.petshield.repository;

import com.example.petshield.domain.DogHealthData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogHealthRepository extends JpaRepository<DogHealthData, Long> {
}
