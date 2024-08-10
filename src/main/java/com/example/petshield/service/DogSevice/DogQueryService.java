package com.example.petshield.service.DogSevice;

import com.example.petshield.domain.Dog;
import com.example.petshield.domain.DogImage;
import com.example.petshield.web.dto.DogRequestDTO;
import org.springframework.web.multipart.MultipartFile;

public interface DogQueryService {
    Dog getMyDogInfo(Long dogId);

    Dog showDogInfo(Long dogId);

    Dog modifyDogInfo(Long dogId, DogRequestDTO.ProfileDto request);

    DogImage mofifyImage(Long dogId, MultipartFile image);
}
