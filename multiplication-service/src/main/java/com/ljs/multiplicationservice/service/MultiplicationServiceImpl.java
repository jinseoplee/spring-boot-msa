package com.ljs.multiplicationservice.service;

import com.ljs.multiplicationservice.dto.MultiplicationAttemptRequest;
import com.ljs.multiplicationservice.dto.MultiplicationDto;
import com.ljs.multiplicationservice.entity.Multiplication;
import com.ljs.multiplicationservice.entity.MultiplicationAttempt;
import com.ljs.multiplicationservice.repository.MultiplicationAttemptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MultiplicationServiceImpl implements MultiplicationService {
    private final MultiplicationAttemptRepository multiplicationAttemptRepository;
    private final RandomGeneratorService randomGeneratorService;

    @Override
    public MultiplicationDto createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new MultiplicationDto(factorA, factorB);
    }

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

        // 곱셈 결과를 반환
        return correct;
    }
}
