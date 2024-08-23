package com.example.petshield.service.DogHealthSevice;

import com.example.petshield.domain.Dog;
import com.example.petshield.domain.DogHealthData;
import com.example.petshield.domain.ObesityData;
import com.example.petshield.repository.DogHealthRepository;
import com.example.petshield.repository.DogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DogHealthCommandServiceImpl implements DogHealthCommandService {

    private final DogRepository dogRepository;
    private final DogHealthRepository dogHealthRepository;

    @Override
    @Transactional
    public DogHealthData getMyDogheart(Long dogId) {
        Dog dog = dogRepository.findById(dogId)
                .orElseThrow(() -> new RuntimeException("Dog not found"));

        List<DogHealthData> dogHealthData = dogHealthRepository.findAllByDogOrderByCreatedAtDesc(dog);
        if (dogHealthData.isEmpty()) {
            throw new RuntimeException("doghealth data not found");
        }

        return dogHealthData.get(0); // Return the first result in the list
    }
}
