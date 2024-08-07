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
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Dog getMyDogInfo() {
        var userId = 1;
        User user = userRepository.findById((long) userId).orElseThrow(() -> new RuntimeException("User not found"));
        return dogRepository.findByUser(user);
    }
}
