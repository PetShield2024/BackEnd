package com.example.petshield.web.controller;

import com.example.petshield.apiPayload.ApiResponse;
import com.example.petshield.converter.FoodConverter;
import com.example.petshield.service.FoodService.FoodQueryService;
import com.example.petshield.web.dto.FoodResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/foods")
public class FoodController {

    private final FoodQueryService foodQueryService;

    @Operation(summary = "사료 목록 조회 API", description = "사료들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @GetMapping("")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content),
    })
    @Parameters({
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<FoodResponseDTO.FoodListDTO> getFoodList(@RequestParam(name = "page") Integer page) {
        // 서비스 호출하여 미션 목록 조회
        var foodList = foodQueryService.getFoodList(page);
        return ApiResponse.onSuccess(FoodConverter.foodListDTO(foodList));
    }
}