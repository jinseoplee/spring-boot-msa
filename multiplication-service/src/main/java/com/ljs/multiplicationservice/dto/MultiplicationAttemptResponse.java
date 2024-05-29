package com.ljs.multiplicationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ljs.multiplicationservice.entity.MultiplicationAttempt;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 곱셈 시도에 대한 응답을 나타내는 DTO 클래스
 */
@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class MultiplicationAttemptResponse {
    @JsonProperty("multiplication")
    private MultiplicationDto multiplicationDto;

    private int answer;
    private boolean correct;

    public static MultiplicationAttemptResponse from(MultiplicationAttempt multiplicationAttempt) {
        MultiplicationDto multiplicationDto = new MultiplicationDto(
                multiplicationAttempt.getMultiplication().getFactorA(),
                multiplicationAttempt.getMultiplication().getFactorB()
        );

        return new MultiplicationAttemptResponse(
                multiplicationDto,
                multiplicationAttempt.getAnswer(),
                multiplicationAttempt.isCorrect()
        );
    }
}
