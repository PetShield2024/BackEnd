package com.example.petshield.web.dto;

import com.example.petshield.domain.enums.Breed;
import com.example.petshield.domain.enums.Extra;
import com.example.petshield.domain.enums.Gender;
import com.example.petshield.domain.enums.Size;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class DogRequestDTO {

    @Getter
    public static class ProfileDto{
        @NotNull
        String dogName;

        String imageUrl;

        @Enumerated(EnumType.STRING)
        @Column(columnDefinition = "VARCHAR(15) DEFAULT '중성화'")
        Gender gender;

        @NotNull
        LocalDate birth;

        @Enumerated(EnumType.STRING)
        Breed breed;

        @NotNull
        Float weight;

        @Enumerated(EnumType.STRING)
        Size size;

        @Enumerated(EnumType.STRING)
        @Column(columnDefinition = "VARCHAR(15) DEFAULT '비임신'")
        Extra extra;
    }
}
