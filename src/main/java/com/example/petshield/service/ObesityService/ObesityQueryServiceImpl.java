package com.example.petshield.service.ObesityService;

import com.example.petshield.apiPayload.code.status.ErrorStatus;
import com.example.petshield.apiPayload.exception.handler.DogIdHandler;
import com.example.petshield.domain.Dog;
import com.example.petshield.domain.ObesityData;
import com.example.petshield.repository.DogRepository;
import com.example.petshield.repository.ObesityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObesityQueryServiceImpl implements ObesityQueryService {
    private final ObesityRepository obesityRepository;
    private final DogRepository dogRepository;

    @Override
    @Transactional
    public ObesityData getImage(Long dogId) {
        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(() -> new RuntimeException("Dog not found"));

        List<ObesityData> obesityDataList = obesityRepository.findAllByDogOrderByCreatedAtDesc(dog);
        if (obesityDataList.isEmpty()) {
            throw new RuntimeException("Obesity data not found");
        }

        return obesityDataList.get(0); // Return the first result in the list
    }
}

