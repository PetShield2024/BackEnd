package com.example.petshield.service.ObesityService;


import com.example.petshield.apiPayload.code.status.ErrorStatus;
import com.example.petshield.apiPayload.exception.handler.DogIdHandler;
import com.example.petshield.aws.s3.AmazonS3Manager;
import com.example.petshield.converter.ObesityConverter;
import com.example.petshield.domain.Dog;
import com.example.petshield.domain.ObesityData;
import com.example.petshield.domain.Uuid;
import com.example.petshield.domain.enums.Obesity;
import com.example.petshield.repository.DogRepository;
import com.example.petshield.repository.ObesityRepository;
import com.example.petshield.repository.UuidRepository;
import com.example.petshield.web.dto.ObesityRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ObesityCommandServiceImpl implements ObesityCommandService {

    private final ObesityRepository obesityRepository;
    private final DogRepository dogRepository;
    private final AmazonS3Manager s3Manager;

    private final UuidRepository uuidRepository;
    private final RestTemplate restTemplate;

    private final String pythonApiUrl = "http://192.168.219.107:5000/predict";  // Python server URL

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

    @Override
    @Transactional
    public Obesity test(Long dogId) {
        Dog dog = dogRepository.findById(dogId).orElseThrow(() -> new RuntimeException("Dog not found"));
        List<ObesityData> obesityDataList = obesityRepository.findAllByDogOrderByCreatedAtDesc(dog);

        try {
            // HTTP GET 요청을 통해 Flask API 호출
            ResponseEntity<Map> response = restTemplate.exchange(
                    pythonApiUrl, HttpMethod.GET, null, Map.class);

            // 응답에서 비만도 예측 결과 추출
            Integer prediction = (Integer) response.getBody().get("obesity");

            // 예측 결과를 Obesity enum으로 매핑
            Obesity obesity;
            switch (prediction) {
                case 0:
                    obesity = Obesity.저체중;
                    break;
                case 1:
                    obesity = Obesity.정상;
                    break;
                case 2:
                    obesity = Obesity.비만;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid obesity prediction value: " + prediction);
            }

            // 최신 ObesityData에 비만도 결과 설정
            obesityDataList.get(0).setObesity(obesity);
            return obesity;

        } catch (Exception e) {
            throw new RuntimeException("Failed to get obesity prediction", e);
        }
    }
}
