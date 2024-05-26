package com.ljs.multiplicationservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomGeneratorServiceImplTest {
    private RandomGeneratorService randomGeneratorService;

    @BeforeEach
    public void setUp() {
        this.randomGeneratorService = new RandomGeneratorServiceImpl();
    }

    @Test
    @DisplayName("11 ~ 99 사이의 무작위 숫자 생성 테스트")
    public void generateRandomFactorIsBetweenExpectedLimits() throws Exception {
        // 무작위 숫자를 생성
        List<Integer> randomFactors = IntStream.range(0, 1000)
                .map(i -> randomGeneratorService.generateRandomFactor())
                .boxed()
                .collect(Collectors.toList());

        // 11 이상 99 이하의 정수인지 확인
        assertTrue(randomFactors.stream().allMatch(f -> 11 <= f && f <= 99));
    }
}