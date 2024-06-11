package com.ljs.memorygame.dto;

import lombok.Getter;

/**
 * 5개의 난수 생성 요청에 따라
 * 5개의 난수를 포함한 배열을 저장하는 DTO
 */
@Getter
public class RandomNumberResponse {
    private int[] randomNumbers;

    public RandomNumberResponse(int[] randomNumbers) {
        this.randomNumbers = randomNumbers;
    }
}
