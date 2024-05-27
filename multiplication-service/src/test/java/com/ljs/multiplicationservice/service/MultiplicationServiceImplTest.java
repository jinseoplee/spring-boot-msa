package com.ljs.multiplicationservice.service;

import com.ljs.multiplicationservice.dto.MultiplicationAttemptRequest;
import com.ljs.multiplicationservice.dto.MultiplicationDto;
import com.ljs.multiplicationservice.entity.MultiplicationAttempt;
import com.ljs.multiplicationservice.repository.MultiplicationAttemptRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


public class MultiplicationServiceImplTest {
    private MultiplicationService multiplicationService;

    @Mock
    private MultiplicationAttemptRepository multiplicationAttemptRepository;

    @Mock
    private RandomGeneratorService randomGeneratorService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        multiplicationService = new MultiplicationServiceImpl(multiplicationAttemptRepository, randomGeneratorService);
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

    @Test
    @DisplayName("무작위 곱셉 정답 테스트")
    public void checkCorrectAnswerTest() {
        // given
        MultiplicationAttemptRequest request = new MultiplicationAttemptRequest("ljs", new MultiplicationDto(10, 20), 200);

        // when
        boolean result = multiplicationService.checkAnswer(request);

        // then
        assertTrue(result);
        verify(multiplicationAttemptRepository).save(any(MultiplicationAttempt.class));
    }

    @Test
    @DisplayName("무작위 곱셈 오답 테스트")
    public void checkWrongAnswerTest() {
        // given
        MultiplicationAttemptRequest request = new MultiplicationAttemptRequest("ljs", new MultiplicationDto(10, 20), 20);

        // when
        boolean result = multiplicationService.checkAnswer(request);

        // then
        assertFalse(result);
        verify(multiplicationAttemptRepository).save(any(MultiplicationAttempt.class));
    }
}