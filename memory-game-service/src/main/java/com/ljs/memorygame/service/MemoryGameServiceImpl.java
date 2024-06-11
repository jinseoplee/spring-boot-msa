package com.ljs.memorygame.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemoryGameServiceImpl implements MemoryGameService {
    private final RandomGeneratorService randomGeneratorService;

    @Override
    public int[] generateFiveRandomNumbers() {
        int[] randomNumbers = new int[5];

        // 5개의 난수를 생성하고 배열에 추가한다.
        for (int i = 0; i < 5; i++) {
            randomNumbers[i] = randomGeneratorService.generateNumber();
        }

        return randomNumbers;
    }
}
