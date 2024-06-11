package com.ljs.memorygame.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomGeneratorServiceImpl implements RandomGeneratorService {
    public static final int MINIMUM = 1;
    public static final int MAXIMUM = 99;

    /**
     * @return 1 ~ 99 사이의 랜덤으로 생성된 정수
     */
    @Override
    public int generateNumber() {
        return new Random().nextInt(MAXIMUM - MINIMUM + 1) + MINIMUM;
    }
}
