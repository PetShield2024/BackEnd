package com.example.petshield.service.ObesityService;

import com.example.petshield.domain.ObesityData;

public interface ObesityQueryService {
    ObesityData getImage(Long dogId);
}
