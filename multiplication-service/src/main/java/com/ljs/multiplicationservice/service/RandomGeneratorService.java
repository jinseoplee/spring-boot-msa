package com.ljs.multiplicationservice.service;

public interface RandomGeneratorService {
    /**
     * @return 무작위로 만든 1 이상 9 이하의 정수
     */
    int generateSingleDigitFactor();

    /**
     * @return 무작위로 만든 10 이상 99 이하의 정수
     */
    int generateTwoDigitFactor();
}
