package com.ljs.multiplicationservice.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomGeneratorServiceImpl implements RandomGeneratorService {
    private final Random random = new Random();

    @Override
    public int generateSingleDigitFactor() {
        return random.nextInt(9) + 1;
    }

    @Override
    public int generateTwoDigitFactor() {
        return random.nextInt(90) + 10;
    }
}
