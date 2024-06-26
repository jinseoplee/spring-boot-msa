package com.ljs.multiplicationservice.service;

import com.ljs.multiplicationservice.dto.MultiplicationAttemptRequest;
import com.ljs.multiplicationservice.dto.MultiplicationAttemptResponse;
import com.ljs.multiplicationservice.dto.MultiplicationDto;
import com.ljs.multiplicationservice.entity.Multiplication;
import com.ljs.multiplicationservice.entity.MultiplicationAttempt;
import com.ljs.multiplicationservice.event.EventDispatcher;
import com.ljs.multiplicationservice.event.MultiplicationSolvedEvent;
import com.ljs.multiplicationservice.repository.MultiplicationAttemptRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Mock
    private EventDispatcher eventDispatcher;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        multiplicationService = new MultiplicationServiceImpl(multiplicationAttemptRepository, randomGeneratorService, eventDispatcher);
    }

    @Test
    @DisplayName("무작위 곱셈 생성 테스트")
    public void createRandomMultiplicationTest() {
        // when
        MultiplicationDto multiplicationDto = multiplicationService.createRandomMultiplication();

        // then
        int factorA = multiplicationDto.getFactorA();
        int factorB = multiplicationDto.getFactorB();

        // 생성된 곱셈의 두 수는 모두 2자리 숫자가 아니어야 한다.
        assertTrue(factorA < 10 || factorB < 10);
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
        verify(eventDispatcher).send(any(MultiplicationSolvedEvent.class));
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
        verify(eventDispatcher).send(any(MultiplicationSolvedEvent.class));
    }

    @Test
    @DisplayName("사용자가 최근 제출한 곱셈 문제를 반환하는 테스트")
    public void getUserRecentAttemptsTest() {
        // given
        Multiplication multiplication = new Multiplication(10, 20);
        MultiplicationAttempt attempt1 = new MultiplicationAttempt("ljs", multiplication, 200, true);
        MultiplicationAttempt attempt2 = new MultiplicationAttempt("ljs", multiplication, 2000, false);

        List<MultiplicationAttempt> recentMultiplicationAttempts = Arrays.asList(attempt1, attempt2);

        List<MultiplicationAttemptResponse> recentMultiplicationAttemptResponses = recentMultiplicationAttempts.stream()
                .map(MultiplicationAttemptResponse::from)
                .collect(Collectors.toList());

        given(multiplicationAttemptRepository.findTop5ByNicknameOrderByIdDesc("ljs"))
                .willReturn(recentMultiplicationAttempts);

        // when
        List<MultiplicationAttemptResponse> result = multiplicationService.getUserRecentAttempts("ljs");

        // then
        assertEquals(recentMultiplicationAttemptResponses, result);
    }

    @Test
    @DisplayName("곱셈 시도 ID로 사용자가 제출한 곱셈 시도를 조회하는 테스트")
    public void getAttemptByIdTest() {
        // given
        Multiplication multiplication = new Multiplication(10, 20);
        MultiplicationAttempt attempt = new MultiplicationAttempt("ljs", multiplication, 200, true);
        MultiplicationAttemptResponse expectedResponse = MultiplicationAttemptResponse.from(attempt);

        given(multiplicationAttemptRepository.findById(1L)).willReturn(Optional.of(attempt));

        // when
        MultiplicationAttemptResponse actualResponse = multiplicationService.getAttemptById(1L);

        // then
        assertEquals(expectedResponse, actualResponse);
    }
}