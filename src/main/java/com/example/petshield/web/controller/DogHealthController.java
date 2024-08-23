package com.example.petshield.web.controller;

import com.example.petshield.apiPayload.ApiResponse;
import com.example.petshield.converter.DogConverter;
import com.example.petshield.converter.DogHealthConverter;
import com.example.petshield.domain.Dog;
import com.example.petshield.domain.DogHealthData;
import com.example.petshield.service.DogHealthSevice.DogHealthCommandService;
import com.example.petshield.web.dto.DogHealthResponseDTO;
import com.example.petshield.web.dto.DogResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dogHealths")
public class DogHealthController {

    @Autowired
    private DogHealthCommandService dogHealthCommandService;

    @Operation(summary = "강아지 심박수 불러오기 API", description = "강아지의 심박수를 불러오는 API입니다.")
    @GetMapping("{dogId}/heart")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content),
    })
    @Parameters({
            @Parameter(name = "dogId", description = "강아지의 아이디, path variable 입니다!")
    })
    public ApiResponse<DogHealthResponseDTO.HeartRateDto> heart(
            @PathVariable(name = "dogId") Long dogId) {
        // 서비스 호출하여 일기 저장
        DogHealthData dogHealthData = dogHealthCommandService.getMyDogheart(dogId);
        return ApiResponse.onSuccess(DogHealthConverter.toHeartDTO(dogHealthData));
    }
}
