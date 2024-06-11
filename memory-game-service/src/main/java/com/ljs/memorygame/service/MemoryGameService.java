package com.ljs.memorygame.service;

public interface MemoryGameService {
    /**
     * 5개의 임의의 정수(1 ~ 99)가 포함된 배열을 생성한다.
     *
     * @return 5개의 임의의 정수(1 ~ 99)를 포함한 배열
     */
    int[] generateFiveRandomNumbers();
}
