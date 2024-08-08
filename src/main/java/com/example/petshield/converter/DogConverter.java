package com.example.petshield.converter;

import com.example.petshield.domain.Dog;
import com.example.petshield.domain.User;
import com.example.petshield.domain.enums.Gender;
import com.example.petshield.web.dto.DogRequestDTO;
import com.example.petshield.web.dto.DogResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DogConverter {

    public static DogResponseDTO.ProfileResultDTO toProfileResultDTO(Dog dog){
        return DogResponseDTO.ProfileResultDTO.builder()
                .dogId(dog.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Dog toDog(User user, DogRequestDTO.ProfileDto request){

        return Dog.builder()
                .user(user)
                .dogName(request.getDogName())
                .imageUrl(request.getImageUrl())
                .gender(request.getGender())
                .breed(request.getBreed())
                .birth(request.getBirth())
                .extra(request.getExtra())
                .size(request.getSize())
                .weight(request.getWeight())
                .activityLevel(request.getActivityLevel())
                .build();
    }

    public static DogResponseDTO.HomeProfileDTO toHomeResultDTO(Dog dog) {
        return DogResponseDTO.HomeProfileDTO.builder()
                .dogName(dog.getDogName())
                .weight(dog.getWeight())
                .birth(dog.getBirth())
                .breed(dog.getBreed())
                .imageUrl(dog.getImageUrl())
                .build();
    }

}
