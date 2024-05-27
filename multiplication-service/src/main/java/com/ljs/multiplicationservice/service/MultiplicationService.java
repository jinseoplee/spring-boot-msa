package com.ljs.multiplicationservice.service;

import com.ljs.multiplicationservice.dto.MultiplicationAttemptRequest;
import com.ljs.multiplicationservice.dto.MultiplicationDto;

public interface MultiplicationService {
    /**
     * 두 개의 무작위 숫자를 담은 {@link MultiplicationDto} 객체를 생성한다.
     * 무작위로 생성되는 숫자의 범위는 11 ~ 99이다.
     *
     * @return 무작위 숫자를 담은 {@link MultiplicationDto} 객체
     */
    MultiplicationDto createRandomMultiplication();

    /**
     * @return 곱셈 결과가 맞으면 true, 틀리면 false
     */
    boolean checkAnswer(MultiplicationAttemptRequest request);
}