package com.example.petshield.domain.enums;

public enum Age {
    UNDER_3_MONTHS("1세 이하"),
    FROM_3_MONTHS_TO_1_YEAR("3개월-1세"),
    FROM_1_TO_7_YEARS("1-7세"),
    OVER_7_YEARS("7세 이상");

    private final String koreanDescription;

    Age(String koreanDescription) {
        this.koreanDescription = koreanDescription;
    }

    public String getKoreanDescription() {
        return koreanDescription;
    }
}
