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
    @DisplayName("1 ~ 10 사이의 무작위 숫자 생성 테스트")
    public void generateSingleDigitFactorTest() throws Exception {
        // 무작위 숫자를 생성
        List<Integer> randomFactors = IntStream.range(0, 1000)
                .map(i -> randomGeneratorService.generateSingleDigitFactor())
                .boxed()
                .collect(Collectors.toList());

        // 1 이상 10 이하의 정수인지 확인
        assertTrue(randomFactors.stream().allMatch(f -> 1 <= f && f <= 10));
    }

    @Test
    @DisplayName("10 ~ 99 사이의 무작위 숫자 생성 테스트")
    public void generateTwoDigitFactorTest() {
        // 무작위 숫자를 생성
        List<Integer> randomFactors = IntStream.range(0, 1000)
                .map(i -> randomGeneratorService.generateTwoDigitFactor())
                .boxed()
                .collect(Collectors.toList());

        // 10 이상 99 이하의 정수인지 확인
        assertTrue(randomFactors.stream().allMatch(f -> 10 <= f && f <= 99));
    }
}