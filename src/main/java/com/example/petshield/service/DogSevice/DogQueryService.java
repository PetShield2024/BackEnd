package com.example.petshield.service.DogSevice;

import com.example.petshield.domain.Dog;
import com.example.petshield.web.dto.DogRequestDTO;
import jakarta.transaction.Transactional;

public interface DogQueryService {
    Dog getMyDogInfo(Long dogId);

    Dog showDogInfo(Long dogId);

    Dog modifyDogInfo(Long dogId, DogRequestDTO.ProfileDto request);
}
