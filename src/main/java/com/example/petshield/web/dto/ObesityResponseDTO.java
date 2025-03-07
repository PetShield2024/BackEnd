package com.example.petshield.web.dto;

import com.example.petshield.domain.enums.Obesity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ObesityResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImageResultDTO{
        Long obesityId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetImageDTO{
        Long obesityId;
        String obesityImage;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestResultDTO {
        Long obesityId;
        Obesity obesity;
    }
}
