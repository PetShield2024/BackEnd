package com.example.petshield.service.DogSevice;

import com.example.petshield.aws.s3.AmazonS3Manager;
import com.example.petshield.converter.DogConverter;
import com.example.petshield.domain.Dog;
import com.example.petshield.domain.DogImage;
import com.example.petshield.domain.User;
import com.example.petshield.domain.Uuid;
import com.example.petshield.repository.DogImageRepository;
import com.example.petshield.repository.DogRepository;
import com.example.petshield.repository.UserRepository;
import com.example.petshield.repository.UuidRepository;
import com.example.petshield.web.dto.DogRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DogCommandServiceImpl implements DogCommandService {

    private final UserRepository userRepository;
    private final DogRepository dogRepository;
    private final DogImageRepository dogImageRepository;

    private final AmazonS3Manager s3Manager;
    private final UuidRepository uuidRepository;


    @Override
    @Transactional
    public Dog profile(Long userId, DogRequestDTO.ProfileDto request) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Dog newDog = DogConverter.toDog(user, request);

        return dogRepository.save(newDog);
    }

    @Override
    public DogImage image(Long dogId, MultipartFile image) {
        Dog dog = dogRepository.findById(dogId).orElseThrow(() -> new RuntimeException("Dog not found"));

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String pictureUrl = s3Manager.uploadFile(s3Manager.generateDogKeyName(savedUuid), image);

        DogImage newDogImage = DogConverter.toDogImage(dog, pictureUrl);

        return dogImageRepository.save(newDogImage);
    }
}
