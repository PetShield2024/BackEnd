package com.example.petshield.repository;

import com.example.petshield.domain.Dog;
import com.example.petshield.domain.ObesityData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ObesityRepository extends JpaRepository<ObesityData, Long> {
    @Query("SELECT o FROM ObesityData o WHERE o.dog = :dog ORDER BY o.createdAt DESC")
    List<ObesityData> findAllByDogOrderByCreatedAtDesc(@Param("dog") Dog dog);
}
