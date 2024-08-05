package com.example.petshield.service.ObesityService;

import com.example.petshield.domain.Dog;
import com.example.petshield.domain.ObesityData;
import com.example.petshield.web.dto.DogRequestDTO;
import com.example.petshield.web.dto.ObesityRequestDTO;
import jakarta.transaction.Transactional;

public interface ObesityCommandService {
    ObesityData saveImage(Long dogId, ObesityRequestDTO.ImageDto request);

}
