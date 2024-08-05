package com.example.petshield.repository;

import com.example.petshield.domain.MyFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyFoodRepository extends JpaRepository<MyFood, Long> {
}
