package com.example.petshield.web.controller;


import com.example.petshield.apiPayload.ApiResponse;
import com.example.petshield.converter.DogConverter;
import com.example.petshield.converter.ObesityConverter;
import com.example.petshield.domain.Dog;
import com.example.petshield.domain.ObesityData;
import com.example.petshield.service.DogSevice.DogCommandService;
import com.example.petshield.service.ObesityService.ObesityCommandService;
import com.example.petshield.service.ObesityService.ObesityQueryService;
import com.example.petshield.web.dto.DogRequestDTO;
import com.example.petshield.web.dto.DogResponseDTO;
import com.example.petshield.web.dto.ObesityRequestDTO;
import com.example.petshield.web.dto.ObesityResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/obesitys")
public class ObesityController {

    private final ObesityCommandService obesityCommandService;
    private final ObesityQueryService obesityQueryService;

    // Constructor-based injection
    @Autowired
    public ObesityController(ObesityCommandService obesityCommandService, ObesityQueryService obesityQueryService) {
        this.obesityCommandService = obesityCommandService;
        this.obesityQueryService = obesityQueryService;
    }
    
    @Operation(summary = "비만도 사진 등록 API", description = "비만도 검사를 위해 업로드한 사진을 저장하는 API입니다.")
    @PostMapping("/{dogId}/image")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content),
    })
    @Parameters({
            @Parameter(name = "dogId", description = "강아지의 아이디, path variable 입니다!")
    })
    public ApiResponse<ObesityResponseDTO.ImageResultDTO> saveImage(
            @PathVariable(name = "dogId") Long dogId,
            @RequestBody @Valid ObesityRequestDTO.ImageDto request) {
        // 서비스 호출하여 일기 저장
        ObesityData obesityData = obesityCommandService.saveImage(dogId, request);
        return ApiResponse.onSuccess(ObesityConverter.toImageResultDTO(obesityData));
    }

    @Operation(summary = "비만도 사진 불러오기 API", description = "비만도 검사를 위해 업로드한 사진을 불러오는 API입니다.")
    @PostMapping("/{dogId}/get")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content),
    })
    @Parameters({
            @Parameter(name = "dogId", description = "강아지의 아이디, path variable 입니다!")
    })
    public ApiResponse<ObesityResponseDTO.GetImageDTO> saveImage(
            @PathVariable(name = "dogId") Long dogId) {
        // 서비스 호출하여 일기 저장
        ObesityData obesityData = obesityQueryService.getImage(dogId);
        return ApiResponse.onSuccess(ObesityConverter.getImageResultDTO(obesityData));
    }
}
