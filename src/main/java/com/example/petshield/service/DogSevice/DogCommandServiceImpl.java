package com.example.petshield.service.DogSevice;

import com.example.petshield.converter.DogConverter;
import com.example.petshield.domain.Dog;
import com.example.petshield.domain.User;
import com.example.petshield.repository.DogRepository;
import com.example.petshield.repository.UserRepository;
import com.example.petshield.web.dto.DogRequestDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DogCommandServiceImpl implements DogCommandService {

    private final UserRepository userRepository;
    private final DogRepository dogRepository;

    @Override
    @Transactional
    public Dog profile(Long userId, DogRequestDTO.ProfileDto request) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Dog newDog = DogConverter.toDog(user, request);

        return dogRepository.save(newDog);
    }
}
