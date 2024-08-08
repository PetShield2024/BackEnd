package com.example.petshield.service.DogSevice;

import com.example.petshield.domain.Dog;
import com.example.petshield.domain.User;
import com.example.petshield.repository.DogRepository;
import com.example.petshield.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DogQueryServiceImpl implements DogQueryService{
    private final DogRepository dogRepository;

    @Override
    @Transactional
    public Dog getMyDogInfo(Long dogId) {
        Dog dog = dogRepository.findById(dogId).orElseThrow(() -> new RuntimeException("Dog not found"));
        return dog;
    }
}
