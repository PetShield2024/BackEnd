package com.example.petshield.web.dto;

import com.example.petshield.domain.enums.Breed;
import com.example.petshield.domain.enums.Extra;
import com.example.petshield.domain.enums.Gender;
import com.example.petshield.domain.enums.Size;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DogResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProfileResultDTO{
        Long dogId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeProfileDTO{
        @NotNull
        String dogName;
        String imageUrl;
        @NotNull
        LocalDate birth;
        @Enumerated(EnumType.STRING)
        Breed breed;
        @NotNull
        Float weight;
    }
}
