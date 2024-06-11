package com.ljs.memorygame.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomGeneratorServiceImplTest {
    private RandomGeneratorService randomGeneratorService = new RandomGeneratorServiceImpl();

    @Test
    @DisplayName("1부터 99까지의 랜덤한 정수 생성 테스트")
    void generateNumberTest() {
        // 무작위 숫자 생성
        List<Integer> numbers = IntStream.range(0, 1000)
                .mapToObj(n -> randomGeneratorService.generateNumber())
                .collect(Collectors.toList());

        // 생성된 숫자가 1 이상 99 이하인지 확인
        assertTrue(numbers.stream().allMatch(n -> n >= 1 && n <= 99));
    }
}