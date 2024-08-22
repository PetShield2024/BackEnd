package com.example.petshield.service.ObesityService;

import com.example.petshield.domain.ObesityData;
import com.example.petshield.domain.enums.Obesity;
import com.example.petshield.web.dto.ObesityRequestDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ObesityCommandService {
    ObesityData saveImage(Long dogId, MultipartFile image);

    Obesity test(Long dogId);
}
