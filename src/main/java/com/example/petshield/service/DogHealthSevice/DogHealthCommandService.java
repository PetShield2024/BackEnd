package com.example.petshield.service.DogHealthSevice;

import com.example.petshield.domain.DogHealthData;

public interface DogHealthCommandService {
    DogHealthData getMyDogheart(Long dogId);
}
