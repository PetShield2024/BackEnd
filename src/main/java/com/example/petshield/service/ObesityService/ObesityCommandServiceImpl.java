package com.example.petshield.service.ObesityService;


import com.example.petshield.apiPayload.code.status.ErrorStatus;
import com.example.petshield.apiPayload.exception.handler.DogIdHandler;
import com.example.petshield.aws.s3.AmazonS3Manager;
import com.example.petshield.converter.ObesityConverter;
import com.example.petshield.domain.Dog;
import com.example.petshield.domain.ObesityData;
import com.example.petshield.domain.Uuid;
import com.example.petshield.repository.DogRepository;
import com.example.petshield.repository.ObesityRepository;
import com.example.petshield.repository.UuidRepository;
import com.example.petshield.web.dto.ObesityRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ObesityCommandServiceImpl implements ObesityCommandService {

    private final ObesityRepository obesityRepository;
    private final DogRepository dogRepository;
    private final AmazonS3Manager s3Manager;

    private final UuidRepository uuidRepository;

    @Override
    @Transactional
    public ObesityData saveImage(Long dogId, MultipartFile image) {

        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(() -> new DogIdHandler(ErrorStatus.DOG_ID_NOT_FOUND));

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String pictureUrl = s3Manager.uploadFile(s3Manager.generateObesityKeyName(savedUuid), image);


        ObesityData newObesityData = ObesityConverter.toObesityData(pictureUrl, dog);

        return obesityRepository.save(newObesityData);
    }
}
