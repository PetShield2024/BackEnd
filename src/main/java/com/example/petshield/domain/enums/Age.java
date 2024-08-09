package com.example.petshield.domain.enums;

public enum Age {
    UNDER_1_YEARS("1세 이하"),
    FROM_1_TO_7_YEARS("1-7세"),
    OVER_7_YEARS("7세 이상"),
    ALL("전체");

    private final String koreanDescription;

    Age(String koreanDescription) {
        this.koreanDescription = koreanDescription;
    }

    public String getKoreanDescription() {
        return koreanDescription;
    }
}
