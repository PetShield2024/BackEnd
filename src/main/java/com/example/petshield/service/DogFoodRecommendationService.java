package com.example.petshield.service;

import com.example.petshield.domain.Dog;
import com.example.petshield.domain.DogHealthData;
import com.example.petshield.domain.ObesityData;
import com.example.petshield.domain.enums.Gender;
import com.example.petshield.repository.DogHealthRepository;
import com.example.petshield.repository.DogRepository;
import com.example.petshield.repository.ObesityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DogFoodRecommendationService {

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private ObesityRepository obesityRepository;

    @Autowired
    private DogHealthRepository dogHealthDataRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String pythonApiUrl = "http://192.168.219.108:5000/recommend";  // Python server URL

    public String getDogFoodRecommendation(Long dogId) {
        Dog dog = dogRepository.findById(dogId).orElseThrow(() -> new RuntimeException("Dog not found"));
        List<ObesityData> obesityDataList = obesityRepository.findAllByDogOrderByCreatedAtDesc(dog);
        DogHealthData healthData = dogHealthDataRepository.findByDog(dog).orElseThrow(() -> new RuntimeException("Health data not found"));

        Map<String, String> dogInfo = new HashMap<>();
        dogInfo.put("심박수", String.valueOf(healthData.getHeartRate()));
        dogInfo.put("하루 걸음 수", String.valueOf(healthData.getStepCount()));
        dogInfo.put("비만도", obesityDataList.get(0).getObesity().toString()); // Assuming Extra represents obesity level
        dogInfo.put("나이", String.valueOf(dog.getAge()));
        dogInfo.put("종", dog.getBreed());
        dogInfo.put("종범위", dog.getSize().toString());
        dogInfo.put("중성화 여부", dog.getGender() == Gender.중성화 ? "예" : "아니오");

        try {
            // JSON 데이터와 Content-Type 헤더를 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // HttpEntity에 JSON 데이터와 헤더를 포함
            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(dogInfo, headers);

            // 데이터 전송
            ResponseEntity<String> response = restTemplate.exchange(pythonApiUrl, HttpMethod.POST, requestEntity, String.class);

            return response.getBody();

        } catch (Exception e) {
            throw new RuntimeException("Failed to get recommendation", e);
        }
    }
}
