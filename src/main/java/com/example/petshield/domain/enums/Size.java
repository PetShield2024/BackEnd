package com.example.petshield.domain.enums;

public enum Size {
    UNDER_3KG("3KG 이하"),
    FROM_3KG_TO_10KG("3KG-10KG"),
    OVER_10KG("10KG 이상");

    private final String koreanDescription;

    Size(String koreanDescription) {
        this.koreanDescription = koreanDescription;
    }

    public String getKoreanDescription() {
        return koreanDescription;
    }
}
