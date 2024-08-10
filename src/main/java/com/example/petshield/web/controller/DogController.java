package com.example.petshield.web.controller;


import com.example.petshield.apiPayload.ApiResponse;
import com.example.petshield.converter.DogConverter;
import com.example.petshield.domain.Dog;
import com.example.petshield.domain.DogImage;
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
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

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

    @Operation(summary = "강아지 사진 등록 API", description = "강아지의 사진을 저장하는 API입니다.")
    @PostMapping(value = "/{dogId}/image", consumes = "multipart/form-data")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content),
    })
    @Parameters({
            @Parameter(name = "dogId", description = "강아지의 아이디, path variable 입니다!")
    })
    public ApiResponse<DogResponseDTO.DogImageResultDTO> imagePost(
            @PathVariable(name = "dogId") Long dogId,
             @RequestPart (value = "image", required = false) MultipartFile image) {
        // 서비스 호출하여 사진 저장
        DogImage dogImage = dogCommandService.image(dogId, image);
        return ApiResponse.onSuccess(DogConverter.toImageResultDTO(dogImage));
    }

    @Operation(summary = "강아지 정보 등록 API", description = "작성한 강아지의 정보를 저장하는 API입니다.")
    @PostMapping(value = "/{userId}/profile")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content),
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자의 아이디, path variable 입니다!")
    })
    public ApiResponse<DogResponseDTO.ProfileResultDTO> profile(
            @PathVariable(name = "userId") Long userId,
            // @RequestPart (value = "image", required = false) MultipartFile image,
            @RequestBody @Valid DogRequestDTO.ProfileDto request) {
        // 서비스 호출하여 일기 저장
        Dog dog = dogCommandService.profile(userId, request);
        return ApiResponse.onSuccess(DogConverter.toProfileResultDTO(dog));
    }


    @Operation(summary = "강아지 프로필 불러오기 API", description = "강아지의 프로필을 불러오는 API입니다.")
    @GetMapping("{dogId}/home")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content),
    })
    @Parameters({
            @Parameter(name = "dogId", description = "강아지의 아이디, path variable 입니다!")
    })
    public ApiResponse<DogResponseDTO.HomeProfileDTO> home(
            @PathVariable(name = "dogId") Long dogId) {
        // 서비스 호출하여 일기 저장
        Dog dog = dogQueryService.getMyDogInfo(dogId);
        return ApiResponse.onSuccess(DogConverter.toHomeResultDTO(dog));
    }

    @Operation(summary = "강아지 정보 조회 API", description = "강아지의 정보를 불러오는 API입니다.")
    @GetMapping("{dogId}/info")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content),
    })
    @Parameters({
            @Parameter(name = "dogId", description = "강아지의 아이디, path variable 입니다!")
    })
    public ApiResponse<DogResponseDTO.InfoDTO> info(
            @PathVariable(name = "dogId") Long dogId) {
        // 서비스 호출하여 일기 저장
        Dog dog = dogQueryService.showDogInfo(dogId);
        return ApiResponse.onSuccess(DogConverter.toInfoResultDTO(dog));
    }


    @Operation(summary = "강아지 정보 수정 API", description = "강아지의 정보를 수정하는 API입니다.")
    @PatchMapping(value = "{dogId}/modify")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content),
    })
    @Parameters({
            @Parameter(name = "dogId", description = "강아지의 아이디, path variable 입니다!")
    })
    public ApiResponse<DogResponseDTO.ModifyResultDTO> modify(
            @PathVariable(name = "dogId") Long dogId,
            // @RequestPart (value = "image", required = false) MultipartFile image,
            @RequestBody @Valid DogRequestDTO.ProfileDto request) {
        // Modify dog information by passing the request DTO
        Dog dog = dogQueryService.modifyDogInfo(dogId, request);
        return ApiResponse.onSuccess(DogConverter.toModifyResultDTO(dog));
    }

    @Operation(summary = "강아지 사진 수정 API", description = "강아지의 사진을 수정하는 API입니다.")
    @PatchMapping(value = "/{dogId}/image/modify", consumes = "multipart/form-data")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content),
    })
    @Parameters({
            @Parameter(name = "dogId", description = "강아지의 아이디, path variable 입니다!")
    })
    public ApiResponse<DogResponseDTO.DogImageModifyDTO> imageModify(
            @PathVariable(name = "dogId") Long dogId,
            @RequestPart (value = "image", required = false) MultipartFile image) {
        // 서비스 호출하여 사진 저장
        DogImage dogImage = dogQueryService.mofifyImage(dogId, image);
        return ApiResponse.onSuccess(DogConverter.toImageModifyDTO(dogImage));
    }

}
