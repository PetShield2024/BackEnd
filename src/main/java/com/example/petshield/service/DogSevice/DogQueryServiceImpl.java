package com.example.petshield.service.DogSevice;

import com.example.petshield.apiPayload.code.status.ErrorStatus;
import com.example.petshield.apiPayload.exception.handler.DogIdHandler;
import com.example.petshield.aws.s3.AmazonS3Manager;
import com.example.petshield.converter.DogConverter;
import com.example.petshield.domain.Dog;
import com.example.petshield.domain.DogImage;
import com.example.petshield.domain.Uuid;
import com.example.petshield.repository.DogImageRepository;
import com.example.petshield.repository.DogRepository;
import com.example.petshield.repository.UuidRepository;
import com.example.petshield.web.dto.DogRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DogQueryServiceImpl implements DogQueryService {
    private final DogRepository dogRepository;
    private final DogImageRepository dogImageRepository;

    private final AmazonS3Manager s3Manager;

    private final UuidRepository uuidRepository;

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

    @Override
    public DogImage mofifyImage(Long dogId, MultipartFile image) {
        DogImage dogImage = dogImageRepository.findById(dogId)
                .orElseThrow(() -> new RuntimeException("DogImage not found"));
        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String pictureUrl = s3Manager.uploadFile(s3Manager.generateDogKeyName(savedUuid), image);

        // DogConverter를 사용하여 강아지 정보 업데이트
        dogImage = DogConverter.updateDogImage(dogImage, pictureUrl);

        // Save the updated dog information
        return dogImageRepository.save(dogImage);
    }
}
