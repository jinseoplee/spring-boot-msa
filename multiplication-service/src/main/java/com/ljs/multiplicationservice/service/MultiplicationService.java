package com.ljs.multiplicationservice.service;

import com.ljs.multiplicationservice.dto.MultiplicationAttemptRequest;
import com.ljs.multiplicationservice.dto.MultiplicationAttemptResponse;
import com.ljs.multiplicationservice.dto.MultiplicationDto;

import java.util.List;

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

    /**
     * 사용자의 최근 곱셈 시도 목록을 반환한다.
     *
     * @param nickname 사용자 닉네임
     * @return 사용자의 최근 곱셈 시도 목록
     */
    List<MultiplicationAttemptResponse> getUserRecentAttempts(String nickname);

    /**
     * ID에 해당하는 곱셈 시도를 조회한다.
     *
     * @param resultId 곱셈 시도 ID
     * @return ID에 해당하는 {@link MultiplicationAttemptResponse} 객체, 없으면 null
     */
    MultiplicationAttemptResponse getAttemptById(Long attemptId);
}
