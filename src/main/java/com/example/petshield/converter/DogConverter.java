package com.example.petshield.converter;

import com.example.petshield.domain.Dog;
import com.example.petshield.domain.User;
import com.example.petshield.domain.enums.Age;
import com.example.petshield.domain.enums.Gender;
import com.example.petshield.web.dto.DogRequestDTO;
import com.example.petshield.web.dto.DogResponseDTO;

import java.time.LocalDate;
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
        // RER 계산
        double rer = calculateRER(request.getWeight());

        // 중성화 여부 확인
        boolean neutered = request.getGender().equals(Gender.중성화);

        // DER 계산
        double der = calculateDER(rer, neutered, calculateAge(request.getBirth()));

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
                .activityLevel(der)  // 활동 대사량으로 사용
                .age(calculateAge(request.getBirth()))  // Assuming there's a field in Dog for age
                .build();
    }


    public static Dog updateDog(Dog dog, DogRequestDTO.ProfileDto request) {
        dog.setDogName(request.getDogName());
        dog.setImageUrl(request.getImageUrl());
        dog.setGender(request.getGender());
        dog.setBirth(request.getBirth());
        dog.setBreed(request.getBreed());
        dog.setWeight(request.getWeight());
        dog.setSize(request.getSize());
        dog.setExtra(request.getExtra());

        // RER 계산
        double rer = calculateRER(request.getWeight());

        // 중성화 여부 확인
        boolean neutered = request.getGender().equals(Gender.중성화);

        // DER 계산
        double der = calculateDER(rer, neutered, calculateAge(request.getBirth()));
        dog.setActivityLevel(der); // activityLevel 업데이트

        // age 필드는 직접 설정하거나 추가로 처리할 수 있습니다.
        dog.setAge(calculateAge(request.getBirth()));

        return dog;
    }


    private static double calculateRER(float weightKg) {
        return 70 * Math.pow(weightKg, 0.75);
    }

    private static double calculateDER(double rer, boolean neutered, int age) {
        double activityFactor;

        if (age < 1) {
            activityFactor = 3.0;  // 강아지의 경우
        } else if (age >= 7) {
            if (neutered) {
                activityFactor = 1.1;  // 중성화한 노견
            } else {
                activityFactor = 1.2;  // 중성화하지 않은 노견
            }
        } else {
            if (neutered) {
                activityFactor = 1.6;  // 중성화한 성견
            } else {
                activityFactor = 1.8;  // 중성화하지 않은 성견
            }
        }

        return rer * activityFactor;
    }

    private static int calculateAge(LocalDate birth) {
        int currentYear = java.time.Year.now().getValue();
        int birthYear = birth.getYear();
        int age = currentYear - birthYear;
        if (java.time.LocalDate.now().isBefore(birth.withYear(currentYear))) {
            age--;  // Subtract one if the birthdate hasn't occurred yet this year
        }
        return age;
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

    public static DogResponseDTO.InfoDTO toInfoResultDTO(Dog dog) {
        return DogResponseDTO.InfoDTO.builder()
                .dogName(dog.getDogName())
                .imageUrl(dog.getImageUrl())
                .gender(dog.getGender())
                .breed(dog.getBreed())
                .birth(dog.getBirth())
                .extra(dog.getExtra())
                .size(dog.getSize())
                .weight(dog.getWeight())
                .build();
    }


    public static DogResponseDTO.ModifyResultDTO toModifyResultDTO(Dog dog){
        return DogResponseDTO.ModifyResultDTO.builder()
                .dogId(dog.getId())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
