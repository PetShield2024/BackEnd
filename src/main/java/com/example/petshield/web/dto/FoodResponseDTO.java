package com.example.petshield.web.dto;

import com.example.petshield.domain.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class FoodResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FoodListDTO{
        List<FoodDTO> foodList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FoodDTO{
        @NotNull
        String foodName;

        @Column(columnDefinition = "VARCHAR(10) DEFAULT 'ALL'")
        String brand;

        @NotNull
        Integer price;

        String image;

        @Enumerated(EnumType.STRING)
        @Column(columnDefinition = "VARCHAR(10)  DEFAULT '정상'")
        Obesity obesity;

        @Enumerated(EnumType.STRING)
        @Column(columnDefinition = "VARCHAR(10) DEFAULT 'ALL'")
        Size size;

        @Enumerated(EnumType.STRING)
        @Column(columnDefinition = "VARCHAR(20) DEFAULT 'ALL'")
        Age age;

        @Enumerated(EnumType.STRING)
        @Column(columnDefinition = "VARCHAR(10) DEFAULT 'ALL'")
        Origin origin;

        String site;

        @Enumerated(EnumType.STRING)
        @Column(columnDefinition = "VARCHAR(10) DEFAULT '비임신'")
        Extra extra;

        LocalDate createdAt;
    }
}