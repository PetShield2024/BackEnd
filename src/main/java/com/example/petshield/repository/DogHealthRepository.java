package com.example.petshield.repository;

import com.example.petshield.domain.Dog;
import com.example.petshield.domain.DogHealthData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface DogHealthRepository extends JpaRepository<DogHealthData, Long> {
    Optional<DogHealthData> findByDog(Dog dog);

    @Query("SELECT o FROM DogHealthData o WHERE o.dog = :dog ORDER BY o.createdAt DESC")
    List<DogHealthData> findAllByDogOrderByCreatedAtDesc(@Param("dog") Dog dog);

    List<DogHealthData> findAllByDogAndCreatedAtBetween(Dog dog, LocalDateTime localDateTime, LocalDateTime localDateTime1);
}
