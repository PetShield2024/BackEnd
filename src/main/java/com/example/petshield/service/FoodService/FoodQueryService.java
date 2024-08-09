package com.example.petshield.service.FoodService;

import com.example.petshield.domain.Food;
import org.springframework.data.domain.Page;

public interface FoodQueryService {
    Page<Food> getFoodList(Integer page);
}
