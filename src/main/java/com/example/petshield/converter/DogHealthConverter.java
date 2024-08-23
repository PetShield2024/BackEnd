package com.example.petshield.converter;

import com.example.petshield.domain.DogHealthData;
import com.example.petshield.web.dto.DogHealthResponseDTO;

public class DogHealthConverter {
    public static DogHealthResponseDTO.HeartRateDto toHeartDTO(DogHealthData dogHealthData) {
        return DogHealthResponseDTO.HeartRateDto.builder()
                .dogHealthDataId(dogHealthData.getId())
                .heartRate(dogHealthData.getHeartRate())
                .build();
    }
}
