package com.example.petshield.converter;

import com.example.petshield.domain.Dog;
import com.example.petshield.domain.ObesityData;
import com.example.petshield.web.dto.DogRequestDTO;
import com.example.petshield.web.dto.DogResponseDTO;
import com.example.petshield.web.dto.ObesityRequestDTO;
import com.example.petshield.web.dto.ObesityResponseDTO;

import java.time.LocalDateTime;

public class ObesityConverter {

    public static ObesityData toObesityData(ObesityRequestDTO.ImageDto request, Dog dog){

        return ObesityData.builder()
                .obesityImage(request.getObesityImage())
                .dog(dog)
                .build();
    }
    public static ObesityResponseDTO.ImageResultDTO toImageResultDTO(ObesityData obesityData) {

        return ObesityResponseDTO.ImageResultDTO.builder()
                .obesityId(obesityData.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
