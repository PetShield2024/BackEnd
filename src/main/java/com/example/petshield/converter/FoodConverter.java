package com.example.petshield.converter;

import com.example.petshield.domain.Food;
import com.example.petshield.domain.enums.Brand;
import com.example.petshield.web.dto.FoodResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class FoodConverter {


    public static FoodResponseDTO.FoodDTO foodDTO(Food food){

        return FoodResponseDTO.FoodDTO.builder()
                .foodName(food.getFoodName())
                .age(food.getAge())
                .image(food.getImage())
                .price(food.getPrice())
                .brand(food.getBrand())
                .site(food.getSite())
                .createdAt(food.getCreatedAt().toLocalDate())
                .extra(food.getExtra())
                .obesity(food.getObesity())
                .origin(food.getOrigin())
                .size(food.getSize())
                .build();
    }
    public static FoodResponseDTO.FoodListDTO foodListDTO(Page<Food> myFoodList){

        List<FoodResponseDTO.FoodDTO> foodDTOList = myFoodList.stream()
                .map(FoodConverter::foodDTO).collect(Collectors.toList());

        return FoodResponseDTO.FoodListDTO.builder()
                .isLast(myFoodList.isLast())
                .isFirst(myFoodList.isFirst())
                .totalPage(myFoodList.getTotalPages())
                .totalElements(myFoodList.getTotalElements())
                .listSize(foodDTOList.size())
                .foodList(foodDTOList)
                .build();
    }
}
