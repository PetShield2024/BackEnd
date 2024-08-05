package com.example.petshield.web.dto;

import com.example.petshield.domain.enums.Breed;
import com.example.petshield.domain.enums.Extra;
import com.example.petshield.domain.enums.Gender;
import com.example.petshield.domain.enums.Size;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class ObesityRequestDTO {

    @Getter
    public static class ImageDto{

        String ObesityImage;
    }
}
