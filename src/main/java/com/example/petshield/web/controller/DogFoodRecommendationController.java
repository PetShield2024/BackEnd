package com.example.petshield.web.controller;

import com.example.petshield.service.DogFoodRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myfoods")
public class DogFoodRecommendationController {

    @Autowired
    private DogFoodRecommendationService dogFoodRecommendationService;

    @PostMapping(value = "/{dogId}/recommend-food", produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> recommendFood(@PathVariable Long dogId) {
        String recommendation = dogFoodRecommendationService.getDogFoodRecommendation(dogId);
        return ResponseEntity.ok(recommendation);
    }
}
