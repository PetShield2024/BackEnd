package com.example.petshield.repository;

import com.example.petshield.domain.Dog;
import com.example.petshield.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<Dog, Long> {
    Dog findByUser(User user);
}
