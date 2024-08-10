package com.example.petshield.repository;

import com.example.petshield.domain.Dog;
import com.example.petshield.domain.DogImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogImageRepository extends JpaRepository<DogImage, Long> {
}
