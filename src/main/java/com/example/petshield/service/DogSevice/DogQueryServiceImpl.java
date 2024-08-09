package com.example.petshield.service.DogSevice;

import com.example.petshield.apiPayload.code.status.ErrorStatus;
import com.example.petshield.apiPayload.exception.handler.DogIdHandler;
import com.example.petshield.converter.DogConverter;
import com.example.petshield.domain.Dog;
import com.example.petshield.domain.User;
import com.example.petshield.domain.enums.Breed;
import com.example.petshield.repository.DogRepository;
import com.example.petshield.repository.UserRepository;
import com.example.petshield.web.dto.DogRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DogQueryServiceImpl implements DogQueryService {
    private final DogRepository dogRepository;

    @Override
    @Transactional
    public Dog getMyDogInfo(Long dogId) {
        Dog dog = dogRepository.findById(dogId).orElseThrow(() -> new DogIdHandler(ErrorStatus.DOG_ID_NOT_FOUND));
        return dog;
    }

    @Override
    @Transactional
    public Dog showDogInfo(Long dogId) {
        Dog dog = dogRepository.findById(dogId).orElseThrow(() -> new DogIdHandler(ErrorStatus.DOG_ID_NOT_FOUND));

        return dog;
    }

    @Override
    @Transactional
    public Dog modifyDogInfo(Long dogId, DogRequestDTO.ProfileDto request) {
        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(() -> new RuntimeException("Dog not found"));

        // DogConverter를 사용하여 강아지 정보 업데이트
        dog = DogConverter.updateDog(dog, request);

        // Save the updated dog information
        return dogRepository.save(dog);
    }
}
