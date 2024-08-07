package com.example.petshield.web.controller;


import com.example.petshield.apiPayload.ApiResponse;
import com.example.petshield.converter.DogConverter;
import com.example.petshield.domain.Dog;
import com.example.petshield.service.DogSevice.DogCommandService;
import com.example.petshield.service.DogSevice.DogQueryService;
import com.example.petshield.web.dto.DogRequestDTO;
import com.example.petshield.web.dto.DogResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dogs")
public class DogController {

    private final DogCommandService dogCommandService;
    private final DogQueryService dogQueryService;

    public DogController(DogCommandService dogCommandService, DogQueryService dogQueryService) {
        this.dogCommandService = dogCommandService;
        this.dogQueryService = dogQueryService;
    }

    // Constructor-based injection


    @Operation(summary = "강아지 정보 등록 API", description = "작성한 강아지의 정보를 저장하는 API입니다.")
    @PostMapping("/profile")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content),
    })
    
    public ApiResponse<DogResponseDTO.ProfileResultDTO> profile(
            @RequestBody @Valid DogRequestDTO.ProfileDto request) {
        // 서비스 호출하여 일기 저장
        Dog dog = dogCommandService.profile(request);
        return ApiResponse.onSuccess(DogConverter.toProfileResultDTO(dog));
    }


    @Operation(summary = "강아지 프로필 불러오기 API", description = "강아지의 프로필을 불러오는 API입니다.")
    @GetMapping("/home")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content),
    })

    public ApiResponse<DogResponseDTO.HomeProfileDTO> home() {
        // 서비스 호출하여 일기 저장
        Dog dog = dogQueryService.getMyDogInfo();
        return ApiResponse.onSuccess(DogConverter.toHomeResultDTO(dog));
    }
}
