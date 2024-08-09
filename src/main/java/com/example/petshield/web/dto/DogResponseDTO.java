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
        @NotNull
        String breed;
        @NotNull
        Float weight;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InfoDTO{
        @NotNull
        String dogName;
        String imageUrl;
        @Enumerated(EnumType.STRING)
        @Column(columnDefinition = "VARCHAR(15) DEFAULT '중성화'")
        Gender gender;
        @NotNull
        LocalDate birth;
        @NotNull
        String breed;
        @NotNull
        Float weight;
        @Enumerated(EnumType.STRING)
        Size size;
        @Enumerated(EnumType.STRING)
        @Column(columnDefinition = "VARCHAR(15) DEFAULT '비임신'")
        Extra extra;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModifyResultDTO{
        Long dogId;
        LocalDateTime updatedAt;
    }
}
