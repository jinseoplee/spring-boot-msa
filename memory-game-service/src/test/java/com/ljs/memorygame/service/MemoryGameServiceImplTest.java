package com.ljs.memorygame.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.BDDMockito.given;

class MemoryGameServiceImplTest {
    private MemoryGameService memoryGameService;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        memoryGameService = new MemoryGameServiceImpl(randomGeneratorService);
    }

    @Test
    @DisplayName("5개의 난수를 포함한 리스트 생성 테스트")
    public void generateFiveRandomNumbersTest() {
        // given
        int[] expected = new int[]{5, 10, 77, 2, 32};

        given(randomGeneratorService.generateNumber())
                .willReturn(5, 10, 77, 2, 32);

        // when
        int[] actual = memoryGameService.generateFiveRandomNumbers();

        // then
        assertArrayEquals(expected, actual);
    }
}