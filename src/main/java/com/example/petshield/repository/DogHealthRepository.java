package com.example.petshield.repository;

import com.example.petshield.domain.Dog;
import com.example.petshield.domain.DogHealthData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DogHealthRepository extends JpaRepository<DogHealthData, Long> {
    Optional<DogHealthData> findByDog(Dog dog);
}
