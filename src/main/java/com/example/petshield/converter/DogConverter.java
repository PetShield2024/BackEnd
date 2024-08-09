package com.example.petshield.converter;

import com.example.petshield.domain.Dog;
import com.example.petshield.domain.User;
import com.example.petshield.domain.enums.Age;
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
    public static Dog toDog(User user, DogRequestDTO.ProfileDto request) {
        int currentYear = java.time.Year.now().getValue();
        int birthYear = request.getBirth().getYear();
        int birthMonth = request.getBirth().getMonthValue();
        int birthDay = request.getBirth().getDayOfMonth();

        int age = currentYear - birthYear;
        if (java.time.LocalDate.now().isBefore(java.time.LocalDate.of(currentYear, birthMonth, birthDay))) {
            age--;  // Subtract one if the birthdate hasn't occurred yet this year
        }

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
                .age(age)  // Assuming there's a field in Dog for age
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
