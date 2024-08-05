package com.example.petshield.service.ObesityService;


import com.example.petshield.apiPayload.code.status.ErrorStatus;
import com.example.petshield.apiPayload.exception.handler.DogIdHandler;
import com.example.petshield.converter.DogConverter;
import com.example.petshield.converter.ObesityConverter;
import com.example.petshield.domain.Dog;
import com.example.petshield.domain.ObesityData;
import com.example.petshield.repository.DogRepository;
import com.example.petshield.repository.ObesityRepository;
import com.example.petshield.web.dto.DogRequestDTO;
import com.example.petshield.web.dto.ObesityRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObesityCommandServiceImpl implements ObesityCommandService {

    private final ObesityRepository obesityRepository;
    private final DogRepository dogRepository;

    @Override
    @Transactional
    public ObesityData saveImage(Long dogId, ObesityRequestDTO.ImageDto request) {

        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(() -> new DogIdHandler(ErrorStatus.DOG_ID_NOT_FOUND));

        ObesityData newObesityData = ObesityConverter.toObesityData(request, dog);

        return obesityRepository.save(newObesityData);
    }
}
