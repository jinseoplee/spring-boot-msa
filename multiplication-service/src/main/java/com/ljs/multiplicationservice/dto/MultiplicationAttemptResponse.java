package com.ljs.multiplicationservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 곱셈 시도에 대한 응답을 나타내는 DTO 클래스
 */
@AllArgsConstructor
@Getter
public class MultiplicationAttemptResponse {
    @JsonProperty("multiplication")
    private MultiplicationDto multiplicationDto;

    private int answer;
    private boolean correct;
}
