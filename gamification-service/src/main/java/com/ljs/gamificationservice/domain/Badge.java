package com.ljs.gamificationservice.domain;

/**
 * 사용자가 획득할 수 있는 여러 종류의 배지
 */
public enum Badge {
    // 점수로 획득하는 배지
    BRONZE_MULTIPLICATOR,
    SILVER_MULTIPLICATOR,
    GOLD_MULTIPLICATOR,

    // 특정 조건으로 획득하는 배지
    FIRST_WON,
    LUCKY_NUMBER
}
