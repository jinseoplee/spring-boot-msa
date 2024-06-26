package com.ljs.multiplicationservice.service;

import com.ljs.multiplicationservice.dto.MultiplicationAttemptRequest;
import com.ljs.multiplicationservice.dto.MultiplicationAttemptResponse;
import com.ljs.multiplicationservice.dto.MultiplicationDto;
import com.ljs.multiplicationservice.entity.Multiplication;
import com.ljs.multiplicationservice.entity.MultiplicationAttempt;
import com.ljs.multiplicationservice.event.EventDispatcher;
import com.ljs.multiplicationservice.event.MultiplicationSolvedEvent;
import com.ljs.multiplicationservice.repository.MultiplicationAttemptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MultiplicationServiceImpl implements MultiplicationService {
    private final MultiplicationAttemptRepository multiplicationAttemptRepository;
    private final RandomGeneratorService randomGeneratorService;
    private final EventDispatcher eventDispatcher;
    private final Random random = new Random();

    @Override
    public MultiplicationDto createRandomMultiplication() {
        int factorA;
        int factorB;

        if (random.nextBoolean()) { // 50% 확률
            factorA = randomGeneratorService.generateSingleDigitFactor();
            factorB = randomGeneratorService.generateTwoDigitFactor();
        } else {
            factorA = randomGeneratorService.generateTwoDigitFactor();
            factorB = randomGeneratorService.generateSingleDigitFactor();
        }

        return new MultiplicationDto(factorA, factorB);
    }

    @Transactional
    @Override
    public boolean checkAnswer(MultiplicationAttemptRequest request) {
        // 정답 채점
        boolean correct = request.getAnswer() ==
                request.getMultiplicationDto().getFactorA() * request.getMultiplicationDto().getFactorB();

        Multiplication multiplication = new Multiplication(
                request.getMultiplicationDto().getFactorA(),
                request.getMultiplicationDto().getFactorB()
        );

        MultiplicationAttempt multiplicationAttempt = new MultiplicationAttempt(
                request.getNickname(),
                multiplication,
                request.getAnswer(),
                correct
        );

        // 답안을 저장
        multiplicationAttemptRepository.save(multiplicationAttempt);

        // 이벤트로 결과를 전송
        eventDispatcher.send(new MultiplicationSolvedEvent(
                multiplicationAttempt.getId(),
                multiplicationAttempt.getNickname(),
                multiplicationAttempt.isCorrect()
        ));

        // 곱셈 결과를 반환
        return correct;
    }

    @Override
    public List<MultiplicationAttemptResponse> getUserRecentAttempts(String nickname) {
        List<MultiplicationAttempt> recentAttempts = multiplicationAttemptRepository.findTop5ByNicknameOrderByIdDesc(nickname);
        return recentAttempts.stream()
                .map(MultiplicationAttemptResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public MultiplicationAttemptResponse getAttemptById(Long attemptId) {
        MultiplicationAttempt savedAttempt = multiplicationAttemptRepository.findById(attemptId)
                .orElseThrow(() -> new NoSuchElementException("값이 존재하지 않습니다."));

        return MultiplicationAttemptResponse.from(savedAttempt);
    }
}
