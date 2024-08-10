package com.example.petshield.service.DogSevice;

import com.example.petshield.domain.Dog;
import com.example.petshield.domain.DogImage;
import com.example.petshield.web.dto.DogRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.web.multipart.MultipartFile;

public interface DogCommandService {
    @Transactional
    Dog profile(Long userId, DogRequestDTO.ProfileDto request);

    DogImage image(Long dogId, MultipartFile image);
}
