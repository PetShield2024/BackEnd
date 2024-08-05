package com.example.petshield.service.DogSevice;

import com.example.petshield.domain.Dog;
import com.example.petshield.web.dto.DogRequestDTO;
import jakarta.transaction.Transactional;

public interface DogCommandService {
    @Transactional
    Dog profile(DogRequestDTO.ProfileDto request);
}
