package com.example.petshield.service.DogSevice;

import com.example.petshield.converter.DogConverter;
import com.example.petshield.domain.Dog;
import com.example.petshield.repository.DogRepository;
import com.example.petshield.web.dto.DogRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DogCommandServiceImpl implements DogCommandService {

    private final DogRepository dogRepository;

    @Override
    @Transactional
    public Dog profile(DogRequestDTO.ProfileDto request) {

        Dog newDog = DogConverter.toDog(request);

        return dogRepository.save(newDog);
    }
}
