package com.ljs.multiplicationservice.service;

import com.ljs.multiplicationservice.dto.MultiplicationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

public class MultiplicationServiceImplTest {
    private MultiplicationService multiplicationService;

    @Mock
    private RandomGeneratorService randomGeneratorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        multiplicationService = new MultiplicationServiceImpl(randomGeneratorService);
    }

    @Test
    @DisplayName("무작위 곱셈 생성 테스트")
    public void createRandomMultiplicationTest() {
        // given (randomGeneratorService가 처음에는 50, 나중에 30을 반환하도록 설정)
        given(randomGeneratorService.generateRandomFactor()).willReturn(50, 30);

        // when
        MultiplicationDto multiplicationDto = multiplicationService.createRandomMultiplication();

        // then
        assertEquals(50, multiplicationDto.getFactorA());
        assertEquals(30, multiplicationDto.getFactorB());
    }
}